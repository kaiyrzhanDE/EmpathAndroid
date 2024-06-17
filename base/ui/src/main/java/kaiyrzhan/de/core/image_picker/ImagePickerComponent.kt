package kaiyrzhan.de.core.image_picker

import android.content.Context
import kaiyrzhan.de.core.image_picker.model.ImagePickerAction
import kaiyrzhan.de.core.image_picker.model.ImagePickerEvent
import kaiyrzhan.de.core.image_picker.model.ImagePickerState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ImagePickerComponent {
    val screenStateFlow: StateFlow<ImagePickerState>

    val _actionsFlow: Channel<ImagePickerAction>
    val actionsFlow: Flow<ImagePickerAction>

    fun onEvent(event: ImagePickerEvent)

    fun getImages(
        context: Context,
        isMultiplePicker: Boolean,
        maxSelectedImages: Int,
        hasImagePermission: Boolean = false,
        hasCameraPermission: Boolean = false,
    )

    fun onDismissClicked()
    fun onAccessClicked()

}