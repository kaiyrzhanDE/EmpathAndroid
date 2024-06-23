package kaiyrzhan.de.auth.login

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.MutableValue
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogComponent
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogConfig
import kaiyrzhan.de.auth.dialog.password_tips_dialog.PasswordTipsComponent
import kaiyrzhan.de.auth.login.model.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeLoginComponent() : LoginComponent {
    override val screenStateFlow: StateFlow<LoginState> = MutableStateFlow(LoginState.Login())
    override val messageDialog = MutableValue(ChildSlot<Any, MessageDialogComponent>())
    override val passwordTipsDialog = MutableValue(ChildSlot<Any, PasswordTipsComponent>())

    override fun onEmailChanged(email: String?)  = Unit
    override fun onPasswordChanged(password: String?) = Unit

    override fun onLoginClicked() = Unit
    override fun onPasswordShowClicked() = Unit
    override fun onPrivacyClicked() = Unit
    override fun onResetPasswordClicked() = Unit
    override fun onRegistrationClicked() = Unit
    override fun onGoogleAuthClicked() = Unit
    override fun onFacebookAuthClicked() = Unit

    override fun showMessageDialog(config: MessageDialogConfig) = Unit
    override fun showPasswordTipsDialog() = Unit
}