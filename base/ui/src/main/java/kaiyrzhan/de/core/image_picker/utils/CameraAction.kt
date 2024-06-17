package kaiyrzhan.de.core.image_picker.utils

import android.net.Uri
import kaiyrzhan.de.core.image_picker.model.ActionType
import kaiyrzhan.de.core.image_picker.model.Image

fun List<Image>.addCameraActionType(actionType: ActionType) =
    listOf(Image(0L, "Camera", Uri.EMPTY, false, null, actionType)) + this
