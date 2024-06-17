package kaiyrzhan.de.core.image_picker.model

sealed class ImagePickerEvent {
    data class OnImageSelected(val image: Image) : ImagePickerEvent()
    data object OnTakePicture : ImagePickerEvent()
    data object OnSendClicked : ImagePickerEvent()
}