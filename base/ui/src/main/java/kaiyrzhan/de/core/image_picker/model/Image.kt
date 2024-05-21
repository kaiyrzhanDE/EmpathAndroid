package kaiyrzhan.de.core.image_picker.model

import android.net.Uri

data class Image(
    val id: Long,
    val name: String,
    val uri: Uri,
    val isSelected: Boolean = false,
    val selectedPosition: Int? = null,
    val actionType: ActionType = ActionType.IMAGE,
)
