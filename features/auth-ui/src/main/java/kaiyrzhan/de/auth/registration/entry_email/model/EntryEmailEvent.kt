package kaiyrzhan.de.auth.registration.entry_email.model

sealed class EntryEmailEvent {
    data class OnEmailChanged(val text: String) : EntryEmailEvent()
    data object OnSendCodeClicked : EntryEmailEvent()
}