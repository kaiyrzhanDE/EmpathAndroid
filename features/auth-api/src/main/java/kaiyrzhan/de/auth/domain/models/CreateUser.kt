package kaiyrzhan.de.auth.domain.models

data class CreateUser(
    val nickname: String,
    val email: String,
    val password: String,
)
