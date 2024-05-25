package kaiyrzhan.de.auth.registration.entry_email

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailAction
import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailState
import kaiyrzhan.de.auth.model.ToolbarState
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RealEntryEmailComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    toolbarState: ToolbarState,
    private val onBackChosen: () -> Unit,
    private val onSendEmailChosen: () -> Unit,
) : ComponentContext by componentContext, EntryEmailComponent {
    private val scope = coroutineScope(coroutineContext + SupervisorJob())

    override val screenStateFlow = MutableStateFlow(EntryEmailState.EntryEmail(toolbarState))

    override fun onEmailChanged(email: String?) =
        screenStateFlow.update { state -> state.copy(email = email.orEmpty()) }

    override fun onSendEmailClicked() {
        onSendEmailChosen()
        scope.launch {
            //request to send code todo
        }
    }

    override fun onBackClicked() = onBackChosen()
}