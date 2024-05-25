package kaiyrzhan.de.auth.registration.entry_email

import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailAction
import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface EntryEmailComponent{
    val screenStateFlow :StateFlow<EntryEmailState>

    fun onEmailChanged(email: String?)
    fun onSendEmailClicked()

    fun onBackClicked()
}