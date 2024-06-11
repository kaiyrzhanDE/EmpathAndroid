package kaiyrzhan.de.auth.login

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kaiyrzhan.de.auth.dialog.MessageDialogComponent
import kaiyrzhan.de.auth.login.model.LoginState
import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {
    val screenStateFlow: StateFlow<LoginState>

    fun onEmailChanged(email: String?)
    fun onPasswordChanged(password: String?)

    fun onLoginClicked()
    fun onPrivacyClicked()
    fun onResetPasswordClicked()
    fun onPasswordShowClicked()
    fun onRegistrationClicked()
    fun onGoogleAuthClicked()
    fun onFacebookAuthClicked()

    fun showErrorDialog(isVisible: Boolean, code: Int? = null)
    fun showPasswordTipsDialog(isVisible: Boolean)

}




