package kaiyrzhan.de.core.token.models

import kotlinx.serialization.*

@Serializable
data class TokenRequest(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String
)
