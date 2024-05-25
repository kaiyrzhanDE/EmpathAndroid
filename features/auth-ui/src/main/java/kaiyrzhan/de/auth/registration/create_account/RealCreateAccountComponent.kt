package kaiyrzhan.de.auth.registration.create_account

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.registration.create_account.model.CreateAccountState
import kaiyrzhan.de.auth.root_navigation.AuthComponent
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RealCreateAccountComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val onBackChosen: () -> Unit,
) : ComponentContext by componentContext, CreateAccountComponent {
    private val scope = coroutineScope(coroutineContext + SupervisorJob())

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
        }
    }

    override fun onBackClicked() = onBackChosen()

}