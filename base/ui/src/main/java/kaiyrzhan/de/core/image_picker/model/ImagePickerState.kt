package kaiyrzhan.de.core.image_picker.model

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