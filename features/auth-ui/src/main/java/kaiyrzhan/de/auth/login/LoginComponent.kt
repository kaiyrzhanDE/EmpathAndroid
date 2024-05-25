package kaiyrzhan.de.auth.login

import kaiyrzhan.de.auth.login.model.LoginAction
import kaiyrzhan.de.auth.login.model.LoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {
    val screenStateFlow: StateFlow<LoginState>

    fun onEmailChanged(email: String?)
    fun onPasswordChanged(password: String?)

    fun onLoginClicked()
    fun onPrivacyClicked()
    fun onResetPasswordClicked()
    fun onRegistrationClicked()
    fun onGoogleAuthClicked()
    fun onFacebookAuthClicked()

    fun showErrorDialog(isVisible: Boolean, code: Int? = null)
    fun showPasswordTipsDialog(isVisible: Boolean)

}




