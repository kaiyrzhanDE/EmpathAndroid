package kaiyrzhan.de.core.image_picker

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.core.image_picker.model.ActionType
import kaiyrzhan.de.core.image_picker.model.Image
import kaiyrzhan.de.core.image_picker.model.ImagePickerAction
import kaiyrzhan.de.core.image_picker.model.ImagePickerEvent
import kaiyrzhan.de.core.image_picker.model.ImagePickerState
import kaiyrzhan.de.core.image_picker.utils.ImageUtils
import kaiyrzhan.de.core.image_picker.utils.addCameraActionType
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RealImagePickerComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val onDismissChosen: () -> Unit,
    private val onAccessChosen: () -> Unit,
) : ComponentContext by componentContext, ImagePickerComponent {
    private val scope = coroutineScope(coroutineContext + SupervisorJob())
    override val screenStateFlow = MutableStateFlow<ImagePickerState>(ImagePickerState.None)

    override val _actionsFlow: Channel<ImagePickerAction> = Channel(capacity = Channel.BUFFERED)
    override val actionsFlow: Flow<ImagePickerAction> = _actionsFlow.receiveAsFlow()

    override fun onDismissClicked() = onDismissChosen()
    override fun onAccessClicked() = onAccessChosen()

    override fun getImages(
        context: Context,
        isMultiplePicker: Boolean,
        maxSelectedImages: Int,
        hasImagePermission: Boolean,
        hasCameraPermission: Boolean,
    ) {
        screenStateFlow.value = ImagePickerState.Loading
        scope.launch {
            val images = ImageUtils.getGalleryImages(context).takeIf { hasImagePermission }
                ?.addCameraActionType(if (hasCameraPermission) ActionType.CAMERA else ActionType.CAMERA_PERMISSION)
                ?: emptyList()

            screenStateFlow.update {
                ImagePickerState.ImagePicker(
                    isMultiplePicker = isMultiplePicker,
                    images = images,
                    maxSelectableImages = maxSelectedImages,
                )

            }
        }
    }

    /**
     * Handles the logic in multiple picker images mode.
     *
     * @param selectedImage The selected image.
     * @param images The current list of images.
     * @param maxSelectableImages The maximum number of images that can be selected.
     * @return The updated list of images.
     */
    private fun handleMultiplePicker(
        selectedImage: Image,
        images: List<Image>,
        maxSelectableImages: Int,
        selectedImages: MutableList<Image>,
    ): Pair<List<Image>, MutableList<Image>> {
        val selectedCount = selectedImages.size
        val updatedImages = images.map { image ->
            when {
                selectedCount < maxSelectableImages && image.id == selectedImage.id -> {
                    if (image.isSelected) {
                        selectedImages.removeIf { it.id == image.id }
                        image.copy(isSelected = false)
                    } else {
                        selectedImages.add(image)
                        image.copy(isSelected = true)
                    }
                }

                selectedCount == maxSelectableImages && image.id == selectedImage.id -> {
                    selectedImages.removeIf { it.id == image.id }
                    image.copy(isSelected = false)
                }

                else -> image
            }
        }

        return Pair(updatedImages, selectedImages)
    }

    private fun handleSinglePicker(
        images: List<Image>,
        selectedImage: Image
    ): Pair<List<Image>, List<Image>> {
        val updatedImages =
            images.map { image -> image.copy(isSelected = image.id == selectedImage.id) }
        return Pair(updatedImages, emptyList())
    }

    private fun updateImagePositions(
        images: List<Image>,
        selectedImages: List<Image>,
    ): List<Image> {
        return images.map { image ->
            image.copy(
                selectedPosition = selectedImages
                    .indexOfFirst { it.id == image.id }
                    .takeIf { it != -1 }
            )
        }
    }

    override fun onEvent(event: ImagePickerEvent) {
        scope.launch {
            val screenState =
                screenStateFlow.value as? ImagePickerState.ImagePicker ?: return@launch
            when (event) {
                is ImagePickerEvent.OnImageSelected -> {
                    val (images, selectedImages) = if (screenState.isMultiplePicker) {
                        handleMultiplePicker(
                            selectedImage = event.image,
                            images = screenState.images,
                            maxSelectableImages = screenState.maxSelectableImages,
                            selectedImages = screenState.selectedImages
                        )
                    } else {
                        handleSinglePicker(
                            images = screenState.images,
                            selectedImage = event.image
                        )
                    }

                    val updatedImages = if (screenState.isMultiplePicker) {
                        updateImagePositions(images, selectedImages)
                    } else {
                        images
                    }

                    screenStateFlow.update {
                        screenState.copy(
                            images = updatedImages,
                            selectedImagesCount = screenState.selectedImages.size,
                        )
                    }
                }

                is ImagePickerEvent.OnSendClicked -> {
                    _actionsFlow.send(ImagePickerAction.OnSendRequest(screenState.selectedImages))
                }

                is ImagePickerEvent.OnTakePicture -> {
                    //TODO
                }
            }
        }
    }


}
