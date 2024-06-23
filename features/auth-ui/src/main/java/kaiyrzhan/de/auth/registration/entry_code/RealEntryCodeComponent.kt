package kaiyrzhan.de.auth.registration.entry_code

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.domain.usecase.VerifyEmailUseCase
import kaiyrzhan.de.auth.registration.entry_code.model.EntryCodeState
import kaiyrzhan.de.auth.model.ToolbarState
import kaiyrzhan.de.core.network.onError
import kaiyrzhan.de.core.network.onSuccess
import kaiyrzhan.de.utils.dispatcher.AppDispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RealEntryCodeComponent(
    componentContext: ComponentContext,
    private val toolbarState: ToolbarState,
    private val email: String,
    private val onBackChosen: () -> Unit,
    private val onRegistrationChosen: () -> Unit,
    private val onResetPasswordChosen: () -> Unit,
) : ComponentContext by componentContext, EntryCodeComponent, KoinComponent {
    private val dispatchers: AppDispatchers by inject()
    private val verifyEmailUseCase: VerifyEmailUseCase by inject()

    private val scope = coroutineScope(dispatchers.main + SupervisorJob())
    override val screenStateFlow = MutableStateFlow(EntryCodeState.EntryCode(toolbarState))

    override fun onCodeChanged(code: String?) =
        screenStateFlow.update { state -> state.copy(code = code.orEmpty()) }

    override fun onRemoveCodeClicked() =
        screenStateFlow.update { state -> state.copy(code = state.code.dropLast(1)) }

    override fun onCheckCodeClicked() {
        scope.launch {
            verifyEmailUseCase.invoke(email, screenStateFlow.value.code)
                .onSuccess {
                    when (toolbarState) {
                        ToolbarState.REGISTRATION -> onRegistrationChosen()
                        ToolbarState.RESET_PASSWORD -> onResetPasswordChosen()
                    }
                }.onError { code, _ ->
                    when(code){
                        //TODO(Handle error on verification email)
                    }
                }
        }
    }

    override fun onBackClicked() = onBackChosen()
}