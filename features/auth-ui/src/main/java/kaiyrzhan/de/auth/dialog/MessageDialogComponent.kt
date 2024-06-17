package kaiyrzhan.de.auth.dialog

import kotlinx.coroutines.flow.StateFlow

interface MessageDialogComponent {
    val screenStateFlow: StateFlow<MessageDialogConfig>

    fun onDismissClicked()
    fun onAccessClicked()
}

