package kaiyrzhan.de.auth.registration.entry_email.model

sealed class EntryEmailAction {
    data object NavigateToEntryCodeScreen: EntryEmailAction()
}