package kaiyrzhan.de.auth.login.model

internal sealed class LoginEvent {
    data class OnPasswordChanged(val text: String) : LoginEvent()
    data class OnUserNameChanged(val text: String) : LoginEvent()
    data object OnLoginClicked : LoginEvent()

    data class ShowErrorDialog(val isVisible: Boolean) : LoginEvent()
    data class ShowPasswordTipsDialog(val isVisible: Boolean) : LoginEvent()
}