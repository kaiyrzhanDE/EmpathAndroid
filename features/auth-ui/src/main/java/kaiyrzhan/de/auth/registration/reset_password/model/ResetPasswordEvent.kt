package kaiyrzhan.de.auth.registration.reset_password.model

sealed class ResetPasswordEvent{
    data class OnPasswordChanged(val text: String): ResetPasswordEvent()
    data class OnRepeatedPasswordChanged(val text: String): ResetPasswordEvent()

    data object ShowPassword: ResetPasswordEvent()
    data object ShowRepeatedPassword: ResetPasswordEvent()

    data object ResetPasswordClicked: ResetPasswordEvent()
}