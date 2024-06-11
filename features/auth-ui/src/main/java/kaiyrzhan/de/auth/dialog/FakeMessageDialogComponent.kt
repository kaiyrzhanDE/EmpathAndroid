package kaiyrzhan.de.auth.dialog

import kotlinx.coroutines.flow.MutableStateFlow

class FakeMessageDialogComponent : MessageDialogComponent {
    override val screenStateFlow =
        MutableStateFlow(MessageDialogConfig.REGISTRATION_EMAIL_ALREADY_EXISTS)

    override fun onDismissClicked() = Unit
    override fun onAccessClicked() = Unit
}