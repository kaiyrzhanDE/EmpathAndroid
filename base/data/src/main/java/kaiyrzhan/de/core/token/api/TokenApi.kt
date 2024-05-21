package kaiyrzhan.de.core.token.api

import kaiyrzhan.de.core.token.models.TokenRequest
import retrofit2.http.POST
import retrofit2.http.Query

interface TokenApi {

    @POST("/api_v1/auth/users/token/refresh")
    suspend fun refreshToken(@Query("token") refreshToken: String): TokenRequest

}