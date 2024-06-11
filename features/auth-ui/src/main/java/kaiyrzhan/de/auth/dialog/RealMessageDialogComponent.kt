package kaiyrzhan.de.auth.dialog

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow

class RealMessageDialogComponent(
    state: MessageDialogConfig,
    private val onAccessChosen: () -> Unit,
    private val onDismissChosen: () -> Unit,
    componentContext: ComponentContext,
) : ComponentContext by componentContext, MessageDialogComponent {
    override val screenStateFlow = MutableStateFlow(state)
    override fun onAccessClicked() = onAccessChosen()
    override fun onDismissClicked() = onDismissChosen()
}