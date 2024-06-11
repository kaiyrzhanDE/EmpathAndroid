package kaiyrzhan.de.auth.registration.entry_email

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kaiyrzhan.de.auth.dialog.MessageDialogComponent
import kaiyrzhan.de.auth.dialog.MessageDialogConfig
import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailState
import kaiyrzhan.de.auth.model.ToolbarState
import kotlinx.coroutines.flow.MutableStateFlow

class FakeEntryEmailComponent(
    toolbarState: ToolbarState = ToolbarState.RESET_PASSWORD
) : EntryEmailComponent {
    override val screenStateFlow = MutableStateFlow(
        EntryEmailState.EntryEmail(toolbarState)
    )
    override val messageDialog = MutableValue(ChildSlot<Any, MessageDialogComponent>())

    override fun onEmailChanged(email: String?) = Unit
    override fun onSendEmailClicked(state: ToolbarState) = Unit

    override fun showMessageDialog(config: MessageDialogConfig) = Unit

    override fun onBackClicked() = Unit
}