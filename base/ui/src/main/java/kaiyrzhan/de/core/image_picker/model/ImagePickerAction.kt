package kaiyrzhan.de.core.image_picker.model

sealed class ImagePickerAction {
    data class OnSendRequest(val selectedImages: List<Image>) : ImagePickerAction()
}