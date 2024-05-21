package kaiyrzhan.de.auth.domain.models

data class VerifyEmail(
    val email: String,
    val code: String,
)