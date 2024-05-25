package kaiyrzhan.de.auth.registration.entry_code.model

sealed class EntryCodeAction {
    data object NavigateToNextScreen : EntryCodeAction()
}