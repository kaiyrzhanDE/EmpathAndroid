package kaiyrzhan.de.auth.registration.entry_email.model

import kaiyrzhan.de.auth.model.ToolbarState

sealed class EntryEmailState {
    data object None : EntryEmailState()
    data object Loading : EntryEmailState()
    data class EntryEmail(
        val toolbarState: ToolbarState,
        val email: String = "",
    ) : EntryEmailState()
}