package kaiyrzhan.de.auth.registration.entry_email

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogComponent
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogConfig
import kaiyrzhan.de.auth.model.ToolbarState
import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailState
import kotlinx.coroutines.flow.StateFlow

interface EntryEmailComponent {
    val screenStateFlow: StateFlow<EntryEmailState>
    val messageDialog: Value<ChildSlot<*, MessageDialogComponent>>

    fun onEmailChanged(email: String?)
    fun onSendEmailClicked(state: ToolbarState)

    fun showMessageDialog(config: MessageDialogConfig)

    fun onBackClicked()
}