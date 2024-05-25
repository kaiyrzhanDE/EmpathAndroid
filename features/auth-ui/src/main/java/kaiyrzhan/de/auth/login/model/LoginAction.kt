package kaiyrzhan.de.auth.login.model

sealed class LoginAction {
    data object OnSuccessLogin : LoginAction()
}