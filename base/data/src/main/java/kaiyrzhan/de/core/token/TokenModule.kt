package kaiyrzhan.de.core.token

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kaiyrzhan.de.core.network.BASE_URL
import kaiyrzhan.de.core.token.api.TokenApi
import kaiyrzhan.de.core.token.mapper.TokenMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


private const val TOKEN_MODULE = "tokenModule"

val tokenModule = module {

    single(named(TOKEN_MODULE)) {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single(named(TOKEN_MODULE)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .addConverterFactory(get<Factory>())
            .client(get<OkHttpClient>(named(TOKEN_MODULE)))
            .build()
    } bind Retrofit::class

    factory { TokenMapper() } bind TokenMapper::class

    single(named(TOKEN_MODULE)) {
        get<Retrofit>(named(TOKEN_MODULE)).create(TokenApi::class.java)
    } bind TokenApi::class

    single { TokenAuthenticator(get()) } bind TokenAuthenticator::class

    single { TokenInterceptor(get()) } bind TokenInterceptor::class

}