package kaiyrzhan.de.auth.registration.reset_password.model

sealed class ResetPasswordState{
    data object None: ResetPasswordState()
    data object Loading: ResetPasswordState()
    data class ResetPassword(
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val repeatedPassword: String = "",
        val isRepeatedPasswordVisible: Boolean = false,
    ): ResetPasswordState()
}