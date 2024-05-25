package kaiyrzhan.de.auth.registration.reset_password

import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.backhandler.BackHandler
import kaiyrzhan.de.auth.registration.reset_password.model.ResetPasswordState
import kotlinx.coroutines.flow.MutableStateFlow

class FakeResetPasswordComponent: ResetPasswordComponent {

    override val backHandler: BackHandler = object: BackHandler{
        override fun register(callback: BackCallback) = Unit
        override fun unregister(callback: BackCallback) = Unit
    }

    override val screenStateFlow = MutableStateFlow<ResetPasswordState>(ResetPasswordState.ResetPassword())

    override fun onPasswordChanged(password: String?) = Unit
    override fun onRepeatedPasswordChanged(password: String?) = Unit

    override fun onPasswordShowClicked() = Unit
    override fun onRepeatedPasswordShowClicked() = Unit
    override fun onResetPasswordClicked() = Unit

    override fun onBackClicked() = Unit

}