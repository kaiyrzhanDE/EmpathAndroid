package kaiyrzhan.de.auth.registration.create_account

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.registration.create_account.model.CreateAccountState
import kaiyrzhan.de.auth.root_navigation.AuthComponent
import kaiyrzhan.de.utils.dispatcher.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject
import kotlin.coroutines.CoroutineContext

class RealCreateAccountComponent(
    componentContext: ComponentContext,
    private val onBackChosen: () -> Unit,
    private val onCreateAccountChosen: () -> Unit,
) : ComponentContext by componentContext, CreateAccountComponent, KoinComponent {
    private val dispatchers: AppDispatchers by inject()
    private val scope = coroutineScope(dispatchers.main + SupervisorJob())

    override val screenStateFlow = MutableStateFlow(CreateAccountState.CreateAccount())

    override fun onNicknameChanged(nickname: String?) =
        screenStateFlow.update { state -> state.copy(nickname = nickname.orEmpty()) }

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

    override fun onCreateAccountClicked() {
        scope.launch {
            //TODO request to creating account
            onCreateAccountChosen()
        }
    }

    override fun onBackClicked() = onBackChosen()

}