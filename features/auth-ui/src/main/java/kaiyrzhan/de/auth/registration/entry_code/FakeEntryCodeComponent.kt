package kaiyrzhan.de.auth.registration.entry_code

import kaiyrzhan.de.auth.registration.entry_code.model.EntryCodeAction
import kaiyrzhan.de.auth.registration.entry_code.model.EntryCodeState
import kaiyrzhan.de.auth.model.ToolbarState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow

class FakeEntryCodeComponent : EntryCodeComponent {

    override val screenStateFlow = MutableStateFlow(
        EntryCodeState.EntryCode(ToolbarState.REGISTRATION)
    )

    override fun onCodeChanged(code: String?) = Unit
    override fun onRemoveCodeClicked() = Unit
    override fun onCheckCodeClicked() = Unit
    override fun onBackClicked() = Unit

}