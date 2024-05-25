package kaiyrzhan.de.auth.registration.create_account.model

sealed class CreateAccountState{
    data object None: CreateAccountState()
    data object Loading: CreateAccountState()
    data class CreateAccount(
        val nickname: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val repeatedPassword: String = "",
        val isRepeatedPasswordVisible: Boolean = false,
    ): CreateAccountState()
}