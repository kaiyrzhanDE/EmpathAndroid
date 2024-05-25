package kaiyrzhan.de.core.token

import kaiyrzhan.de.core.shared_preferences.TokenPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Semaphore

class TokenAuthenticator constructor(
//    @AuthQualifier private val refreshTokenUseCase: RefreshTokenUseCase,
    private val tokenPreferences: TokenPreferences,
) : Authenticator {
    private val locker = Semaphore(1)

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = requestToken()
        return if (!token.isNullOrBlank()) {
            rewriteRequest(response.request, token)
        } else {
            tokenPreferences.deleteToken()
            //TODO Logout logic
            null
        }
    }

    private fun requestToken(): String? = runBlocking {
        try {
            locker.acquire()
            val oldToken = tokenPreferences.getToken()

            if (oldToken?.accessToken != null) {
//                val token = refreshToken(oldToken)
//                tokenPreferences.deleteToken()
//                tokenPreferences.saveToken(token)
            }
            tokenPreferences.getToken()?.accessToken
        } catch (e: HttpException) {
            null
        } catch (e: Exception) {
            throw IOException("Can't refresh token", e)
        }
    }

    private fun rewriteRequest(request: Request, token: String): Request {
        return request.newBuilder()
            .removeHeader(TokenInterceptor.AUTH_HEADER)
            .addHeader(TokenInterceptor.AUTH_HEADER, "${TokenInterceptor.BEARER} $token")
            .build()
    }

//    private suspend fun refreshToken(token: Token): Token {
//        return refreshTokenUseCase.invoke(token.refreshToken)
//    }
}
