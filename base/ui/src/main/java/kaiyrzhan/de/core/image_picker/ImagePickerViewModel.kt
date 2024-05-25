package kaiyrzhan.de.core.image_picker

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaiyrzhan.de.core.image_picker.model.ActionType
import kaiyrzhan.de.core.image_picker.model.Image
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ImagePickerViewModel : ViewModel() {

    private val _screenStateFlow: MutableStateFlow<ImagePickerState> =
        MutableStateFlow(ImagePickerState.None)
    val screenStateFlow: StateFlow<ImagePickerState>
        get() = _screenStateFlow.asStateFlow()

    private val _actionsFlow = Channel<ImagePickerAction>(capacity = Channel.BUFFERED)
    val actionsFlow: Flow<ImagePickerAction> get() = _actionsFlow.receiveAsFlow()

    private fun List<Image>.addCameraActionType(actionType: ActionType) =
        listOf(Image(0L, "Camera", Uri.EMPTY, false, null, actionType)) + this

    fun getImages(
        context: Context,
        isMultiplePicker: Boolean,
        maxSelectedImages: Int,
        hasImagePermission: Boolean = false,
        hasCameraPermission: Boolean = false,
    ) {
        _screenStateFlow.value = ImagePickerState.Loading
        viewModelScope.launch {
            val images = ImageUtils.getGalleryImages(context).takeIf { hasImagePermission }
                ?.addCameraActionType(if (hasCameraPermission) ActionType.CAMERA else ActionType.CAMERA_PERMISSION)
                ?: emptyList()

            _screenStateFlow.value = ImagePickerState.ImagePicker(
                isMultiplePicker = isMultiplePicker,
                images = images,
                maxSelectableImages = maxSelectedImages,
            )
        }
    }

    fun onEvent(event: ImagePickerEvent) {
        viewModelScope.launch {
            val screenState =
                _screenStateFlow.value as? ImagePickerState.ImagePicker ?: return@launch
            when (event) {
                is ImagePickerEvent.OnImageSelected -> {

                    val (images, selectedImages) = if (screenState.isMultiplePicker) {
                        handleMultiplePicker(
                            event.image,
                            screenState.images,
                            screenState.maxSelectableImages,
                            screenState.selectedImages
                        )
                    } else {
                        handleSinglePicker(screenState.images, event.image)
                    }

                    val updatedImages = if (screenState.isMultiplePicker) {
                        updateImagePositions(images, selectedImages)
                    } else {
                        images
                    }

                    _screenStateFlow.value = screenState.copy(
                        images = updatedImages,
                        selectedImagesCount = screenState.selectedImages.size,
                    )
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

}

sealed class ImagePickerState {
    data object None : ImagePickerState()
    data object Loading : ImagePickerState()
    data class ImagePicker(
        val isMultiplePicker: Boolean,
        val maxSelectableImages: Int,
        val selectedImagesCount: Int = 0,
        val images: List<Image> = emptyList(),
        val selectedImages: MutableList<Image> = mutableListOf(),
    ) : ImagePickerState()
}

sealed class ImagePickerEvent {
    data class OnImageSelected(val image: Image) : ImagePickerEvent()
    data object OnTakePicture : ImagePickerEvent()
    data object OnSendClicked : ImagePickerEvent()
}

sealed class ImagePickerAction {
    data class OnSendRequest(val selectedImages: List<Image>) : ImagePickerAction()
}