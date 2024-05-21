package kaiyrzhan.de.auth.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)