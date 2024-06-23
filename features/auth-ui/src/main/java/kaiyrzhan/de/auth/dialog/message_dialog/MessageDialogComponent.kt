package kaiyrzhan.de.auth.dialog.message_dialog

import kotlinx.coroutines.flow.StateFlow

interface MessageDialogComponent {

    val screenStateFlow: StateFlow<MessageDialogConfig>

    fun onDismissClicked()
    fun onAccessClicked()
}

