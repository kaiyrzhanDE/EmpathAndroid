package kaiyrzhan.de.auth.registration.entry_email

import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailAction
import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailState
import kaiyrzhan.de.auth.model.ToolbarState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow

class FakeEntryEmailComponent : EntryEmailComponent {
    override val screenStateFlow = MutableStateFlow(
        EntryEmailState.EntryEmail(ToolbarState.REGISTRATION)
    )

    override fun onEmailChanged(email: String?) = Unit
    override fun onSendEmailClicked() = Unit

    override fun onBackClicked() = Unit
}