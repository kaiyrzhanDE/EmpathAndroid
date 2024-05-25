package kaiyrzhan.de.auth.registration.entry_code

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.registration.entry_code.model.EntryCodeAction
import kaiyrzhan.de.auth.registration.entry_code.model.EntryCodeState
import kaiyrzhan.de.auth.model.ToolbarState
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RealEntryCodeComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val toolbarState: ToolbarState,
    private val onBackChosen :() -> Unit,
    private val onRegistrationChosen: () -> Unit,
    private val onResetPasswordChosen: () -> Unit,
) : ComponentContext by componentContext, EntryCodeComponent {
    private val scope = coroutineScope(coroutineContext + SupervisorJob())

    override val screenStateFlow = MutableStateFlow(EntryCodeState.EntryCode(toolbarState))

    override fun onCodeChanged(code: String?) =
        screenStateFlow.update { state -> state.copy(code = code.orEmpty()) }

    override fun onRemoveCodeClicked() =
        screenStateFlow.update { state -> state.copy(code = state.code.dropLast(1)) }

    override fun onCheckCodeClicked() {
        when(toolbarState){
            ToolbarState.REGISTRATION -> onRegistrationChosen()
            ToolbarState.RESET_PASSWORD -> onResetPasswordChosen()
        }
        scope.launch {
            // request to check code todo
        }
    }

    override fun onBackClicked() = onBackChosen()
}