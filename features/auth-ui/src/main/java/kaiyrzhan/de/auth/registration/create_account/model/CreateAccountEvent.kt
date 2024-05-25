package kaiyrzhan.de.auth.registration.create_account.model

internal sealed class CreateAccountEvent{
    data class OnNicknameChanged(val text: String): CreateAccountEvent()
    data class OnPasswordChanged(val text: String): CreateAccountEvent()
    data class OnRepeatedPasswordChanged(val text: String): CreateAccountEvent()

    data object ShowPassword: CreateAccountEvent()
    data object ShowRepeatedPassword: CreateAccountEvent()

    data object CreateAccountClicked: CreateAccountEvent()
}