package kaiyrzhan.de.auth.login

import kaiyrzhan.de.auth.login.model.LoginAction
import kaiyrzhan.de.auth.login.model.LoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class FakeLoginComponent : LoginComponent {
    override val screenStateFlow: StateFlow<LoginState> = MutableStateFlow(LoginState.Login())

    override fun onEmailChanged(email: String?)  = Unit
    override fun onPasswordChanged(password: String?) = Unit

    override fun onLoginClicked() = Unit
    override fun onPrivacyClicked() = Unit
    override fun onResetPasswordClicked() = Unit
    override fun onRegistrationClicked() = Unit
    override fun onGoogleAuthClicked() = Unit
    override fun onFacebookAuthClicked() = Unit

    override fun showErrorDialog(isVisible: Boolean, code: Int?) = Unit
    override fun showPasswordTipsDialog(isVisible: Boolean) = Unit
}