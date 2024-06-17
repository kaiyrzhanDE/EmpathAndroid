package kaiyrzhan.de.core.image_picker

import android.content.Context
import kaiyrzhan.de.core.image_picker.model.ImagePickerAction
import kaiyrzhan.de.core.image_picker.model.ImagePickerEvent
import kaiyrzhan.de.core.image_picker.model.ImagePickerState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class FakeImagePickerComponent : ImagePickerComponent {
    override val screenStateFlow: StateFlow<ImagePickerState> = MutableStateFlow(
        ImagePickerState.ImagePicker(
            isMultiplePicker = false,
            selectedImages = mutableListOf(),
            maxSelectableImages = 9,
            selectedImagesCount = 0,
            images = emptyList()
        )
    )
    override val _actionsFlow: Channel<ImagePickerAction> = Channel(capacity = Channel.BUFFERED)
    override val actionsFlow: Flow<ImagePickerAction> = _actionsFlow.receiveAsFlow()

    override fun onEvent(event: ImagePickerEvent) = Unit
    override fun getImages(
        context: Context,
        isMultiplePicker: Boolean,
        maxSelectedImages: Int,
        hasImagePermission: Boolean,
        hasCameraPermission: Boolean
    )  = Unit

    override fun onDismissClicked() = Unit
    override fun onAccessClicked() = Unit

}
