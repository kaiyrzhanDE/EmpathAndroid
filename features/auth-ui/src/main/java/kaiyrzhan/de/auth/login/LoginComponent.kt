package kaiyrzhan.de.auth.login

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogComponent
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogConfig
import kaiyrzhan.de.auth.dialog.password_tips_dialog.PasswordTipsComponent
import kaiyrzhan.de.auth.login.model.LoginState
import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {
    val screenStateFlow: StateFlow<LoginState>
    val messageDialog: Value<ChildSlot<*, MessageDialogComponent>>
    val passwordTipsDialog: Value<ChildSlot<*, PasswordTipsComponent>>

    fun onEmailChanged(email: String?)
    fun onPasswordChanged(password: String?)

    fun onLoginClicked()
    fun onPrivacyClicked()
    fun onResetPasswordClicked()
    fun onPasswordShowClicked()
    fun onRegistrationClicked()
    fun onGoogleAuthClicked()
    fun onFacebookAuthClicked()

    fun showMessageDialog(config: MessageDialogConfig)
    fun showPasswordTipsDialog()

}




