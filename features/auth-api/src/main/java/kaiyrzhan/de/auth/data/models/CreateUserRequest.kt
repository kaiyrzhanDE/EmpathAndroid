package kaiyrzhan.de.auth.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    @SerialName("nickname") val nickname: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)