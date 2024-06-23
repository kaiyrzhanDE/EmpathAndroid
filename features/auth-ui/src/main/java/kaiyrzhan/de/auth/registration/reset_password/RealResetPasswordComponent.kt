package kaiyrzhan.de.auth.registration.reset_password

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.backhandler.BackHandler
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.registration.reset_password.model.ResetPasswordState
import kaiyrzhan.de.utils.dispatcher.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class RealResetPasswordComponent(
    componentContext: ComponentContext,
    private val onBackChosen: () -> Unit,
) : ComponentContext by componentContext, ResetPasswordComponent, KoinComponent {
    private val dispatchers: AppDispatchers by inject()
    private val scope = coroutineScope(dispatchers.main + SupervisorJob())

    override val screenStateFlow = MutableStateFlow(ResetPasswordState.ResetPassword())

    override fun onPasswordChanged(password: String?) =
        screenStateFlow.update { state -> state.copy(password = password.orEmpty()) }

    override fun onRepeatedPasswordChanged(password: String?) =
        screenStateFlow.update { state -> state.copy(repeatedPassword = password.orEmpty()) }

    override fun onPasswordShowClicked() =
        screenStateFlow.update { state -> state.copy(isPasswordVisible = !state.isPasswordVisible) }

    override fun onRepeatedPasswordShowClicked() =
        screenStateFlow.update { state ->
            state.copy(isRepeatedPasswordVisible = !state.isRepeatedPasswordVisible)
        }

    override fun onResetPasswordClicked() {
        scope.launch {
            //request to reset password todo
        }
    }

    override fun onBackClicked() = onBackChosen()
}