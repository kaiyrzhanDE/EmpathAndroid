package kaiyrzhan.de.core.error_dialog

import kaiyrzhan.de.core_ui.R


data class ErrorDialogState(
    val isVisible: Boolean = false,
    val titleResId: Int = R.string.oops_something_be_wrong,
    val description: String? = null,
    val iconResId: Int = R.drawable.ic_attention,
)