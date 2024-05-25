package kaiyrzhan.de.auth.registration.entry_code.model

internal sealed class EntryCodeEvent {
    data class OnCodeChange(val text: String) : EntryCodeEvent()
    data object OnRemoveCodeClicked : EntryCodeEvent()
    data object OnCheckCodeClicked : EntryCodeEvent()
}