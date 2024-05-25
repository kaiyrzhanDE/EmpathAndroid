package kaiyrzhan.de.auth.registration.entry_code.model

import kaiyrzhan.de.auth.model.ToolbarState

sealed class EntryCodeState {
    data object None : EntryCodeState()
    data object Loading : EntryCodeState()
    data class EntryCode(
        val toolbarState: ToolbarState,
        val code: String = "",
    ) : EntryCodeState()
}