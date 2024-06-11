package kaiyrzhan.de.auth.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.domain.usecase.LoginUseCase
import kaiyrzhan.de.auth.login.model.LoginState
import kaiyrzhan.de.core.network.onError
import kaiyrzhan.de.core.network.onSuccess
import kaiyrzhan.de.core.token.usecase.SaveTokenUseCase
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext
import org.koin.core.component.inject

class RealLoginComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val onRegistrationChosen: () -> Unit,
    private val onPrivacyChosen: () -> Unit,
    private val onResetPasswordChosen: () -> Unit,
    private val onLoginSuccess: () -> Unit,
) : ComponentContext by componentContext, LoginComponent, KoinComponent {
    private val loginUseCase: LoginUseCase by inject()
    private val saveTokenUseCase: SaveTokenUseCase by inject()

    private val scope = coroutineScope(coroutineContext + SupervisorJob())
    override val screenStateFlow = MutableStateFlow(LoginState.Login())

    override fun onEmailChanged(email: String?) =
        screenStateFlow.update { state -> state.copy(email = email.orEmpty()) }

    override fun onPasswordChanged(password: String?) =
        screenStateFlow.update { state -> state.copy(password = password.orEmpty()) }

    override fun onLoginClicked() {
        val state = screenStateFlow.value
        scope.launch {
            if (!PasswordRegex.matches(state.password)) {
                showPasswordTipsDialog(true)
                return@launch
            }
            loginUseCase(
                email = state.email,
                password = state.password
            ).onError { code, _ ->
                if (code != 422) showErrorDialog(isVisible = true, code = code)
                else showPasswordTipsDialog(true)
            }.onSuccess { token ->
                //TODO navigate to main
                saveTokenUseCase(token)
            }
        }
    }

    override fun onPasswordShowClicked() =
        screenStateFlow.update { state -> state.copy(isPasswordVisible = !state.isPasswordVisible) }

    override fun onPrivacyClicked() {
        onPrivacyChosen()
    }

    override fun onResetPasswordClicked() {
        onResetPasswordChosen()
    }

    override fun onRegistrationClicked() {
        onRegistrationChosen()
    }

    override fun onGoogleAuthClicked() {
//        TODO("Need to be implemented with Google api in the future")
    }

    override fun onFacebookAuthClicked() {
//        TODO("Need to be implemented with Facebook api in the future")
    }

    override fun showPasswordTipsDialog(isVisible: Boolean) =
        screenStateFlow.update { state -> state.copy(isPasswordTipsDialogVisible = isVisible) }

    override fun showErrorDialog(isVisible: Boolean, code: Int?) {
        screenStateFlow.update { state ->
            state.copy(
                errorDialogState = state.errorDialogState.copy(code = code, isVisible = isVisible)
            )
        }
    }

    companion object {
        private val PasswordRegex =
            Regex("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,128}$")
    }
}