package kaiyrzhan.de.auth.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogComponent
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogConfig
import kaiyrzhan.de.auth.dialog.message_dialog.RealMessageDialogComponent
import kaiyrzhan.de.auth.dialog.password_tips_dialog.PasswordTipsComponent
import kaiyrzhan.de.auth.dialog.password_tips_dialog.PasswordTipsDialogConfig
import kaiyrzhan.de.auth.dialog.password_tips_dialog.RealPasswordTipsComponent
import kaiyrzhan.de.auth.domain.usecase.LoginUseCase
import kaiyrzhan.de.auth.login.model.LoginState
import kaiyrzhan.de.core.network.onError
import kaiyrzhan.de.core.network.onSuccess
import kaiyrzhan.de.core.token.usecase.SaveTokenUseCase
import kaiyrzhan.de.utils.dispatcher.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext
import org.koin.core.component.inject

class RealLoginComponent(
    componentContext: ComponentContext,
    private val onRegistrationChosen: () -> Unit,
    private val onPrivacyChosen: () -> Unit,
    private val onResetPasswordChosen: () -> Unit,
    private val onLoginSuccess: () -> Unit,
) : ComponentContext by componentContext, LoginComponent, KoinComponent {
    private val dispatchers: AppDispatchers by inject()
    private val loginUseCase: LoginUseCase by inject()
    private val saveTokenUseCase: SaveTokenUseCase by inject()

    private val scope = coroutineScope(dispatchers.main + SupervisorJob())
    override val screenStateFlow = MutableStateFlow(LoginState.Login())

    private val messageDialogNavigation = SlotNavigation<MessageDialogConfig>()
    override val messageDialog: Value<ChildSlot<*, MessageDialogComponent>> = childSlot(
        source = messageDialogNavigation,
        key = "message",
        serializer = MessageDialogConfig.serializer(),
        childFactory = { config, childComponentContext ->
            RealMessageDialogComponent(
                componentContext = childComponentContext,
                state = config,
                onAccessChosen = messageDialogNavigation::dismiss,
                onDismissChosen = messageDialogNavigation::dismiss,
            )
        },
    )

    private val passwordTipsNavigation = SlotNavigation<PasswordTipsDialogConfig>()
    override val passwordTipsDialog: Value<ChildSlot<*, PasswordTipsComponent>> = childSlot(
        source = passwordTipsNavigation,
        key = "passwordTips",
        serializer = PasswordTipsDialogConfig.serializer(),
        childFactory = { _, childComponentContext ->
            RealPasswordTipsComponent(
                componentContext = childComponentContext,
                onDismissChosen = passwordTipsNavigation::dismiss,
            )
        },
    )

    override fun showPasswordTipsDialog() =
        passwordTipsNavigation.activate(PasswordTipsDialogConfig.UNKNOWN_ERROR)

    override fun showMessageDialog(config: MessageDialogConfig) =
        messageDialogNavigation.activate(config)

    override fun onEmailChanged(email: String?) =
        screenStateFlow.update { state -> state.copy(email = email.orEmpty()) }

    override fun onPasswordChanged(password: String?) =
        screenStateFlow.update { state -> state.copy(password = password.orEmpty()) }

    override fun onPasswordShowClicked() =
        screenStateFlow.update { state -> state.copy(isPasswordVisible = !state.isPasswordVisible) }

    override fun onPrivacyClicked() = onPrivacyChosen()
    override fun onResetPasswordClicked() = onResetPasswordChosen()
    override fun onRegistrationClicked() = onRegistrationChosen()

    override fun onGoogleAuthClicked() {
//        TODO("Need to be implemented with Google api in the future")
    }

    override fun onFacebookAuthClicked() {
//        TODO("Need to be implemented with Facebook api in the future")
    }

    override fun onLoginClicked() {
        val state = screenStateFlow.value
        scope.launch {
            if (!PasswordRegex.matches(state.password)) {
                showPasswordTipsDialog()
                return@launch
            }
//            loginUseCase(
//                email = state.email,
//                password = state.password
//            ).onError { code, _ ->
//                when (code) {
//                    422 -> showPasswordTipsDialog()
//                    429 -> showMessageDialog(MessageDialogConfig.TOO_MANY_LOGIN_ATTEMPTS)
//                    else -> showMessageDialog(MessageDialogConfig.UNKNOWN_ERROR)
//                }
//            }.onSuccess { token ->
//                saveTokenUseCase(token)
//                onLoginSuccess()
//            }
            onLoginSuccess()
        }
    }

    companion object {
        private val PasswordRegex =
            Regex("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,128}$")
    }
}