package kaiyrzhan.de.auth.domain.models

data class ResetPassword(
    val email: String,
    val password: String,
)