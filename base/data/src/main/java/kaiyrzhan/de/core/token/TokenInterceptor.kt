package kaiyrzhan.de.core.token

import kaiyrzhan.de.core.shared_preferences.TokenPreferences
import kaiyrzhan.de.utils.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor(
    private val tokenPreferences: TokenPreferences,
    private val logger: Logger,
): Interceptor {

    companion object{
        const val AUTH_HEADER = "Authorization"
        const val BEARER = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .method(originalRequest.method, originalRequest.body)

        val authToken = tokenPreferences.getToken()
        if (authToken?.accessToken != null) {
            requestBuilder.addHeader(AUTH_HEADER, "$BEARER ${authToken.accessToken}")
            logger.e("TokenInterceptor", "Bearer ${authToken.accessToken}")
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }




}