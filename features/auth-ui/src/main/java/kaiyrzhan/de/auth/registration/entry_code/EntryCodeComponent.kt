package kaiyrzhan.de.auth.registration.entry_code

import kaiyrzhan.de.auth.registration.entry_code.model.EntryCodeAction
import kaiyrzhan.de.auth.registration.entry_code.model.EntryCodeState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

interface EntryCodeComponent {
    val screenStateFlow: StateFlow<EntryCodeState>

    fun onCodeChanged(code: String?)

    fun onRemoveCodeClicked()
    fun onCheckCodeClicked()

    fun onBackClicked()
}