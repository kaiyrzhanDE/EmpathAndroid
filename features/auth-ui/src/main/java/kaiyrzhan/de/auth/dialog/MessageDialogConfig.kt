package kaiyrzhan.de.auth.dialog

import kotlinx.serialization.Serializable
@Serializable
enum class MessageDialogConfig{
    REGISTRATION_EMAIL_ALREADY_EXISTS,
    RESET_PASSWORD_EMAIL_NOT_REGISTERED,
    ABORT_REGISTRATION,
    ABORT_RESET_PASSWORD,
    UNKNOWN_ERROR,
}