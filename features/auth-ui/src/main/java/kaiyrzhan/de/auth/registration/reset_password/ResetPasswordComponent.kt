package kaiyrzhan.de.auth.registration.reset_password

import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kaiyrzhan.de.auth.registration.reset_password.model.ResetPasswordState
import kotlinx.coroutines.flow.StateFlow
import java.nio.channels.Channel

interface ResetPasswordComponent: BackHandlerOwner {
    val screenStateFlow: StateFlow<ResetPasswordState>

    fun onPasswordChanged(password: String?)
    fun onRepeatedPasswordChanged(password: String?)

    fun onPasswordShowClicked()
    fun onRepeatedPasswordShowClicked()
    fun onResetPasswordClicked()

    fun onBackClicked()
}