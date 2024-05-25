package kaiyrzhan.de.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kaiyrzhan.de.core.token.TokenAuthenticator
import kaiyrzhan.de.core.token.TokenInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

internal const val BASE_URL = "http://192.168.1.227"

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .authenticator(get<TokenAuthenticator>())
            .addInterceptor(get<TokenInterceptor>())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    } bind OkHttpClient::class

    factory { Json.asConverterFactory("application/json".toMediaType()) } bind Factory::class

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .addConverterFactory(get<Factory>())
            .client(get<OkHttpClient>())
            .build()
    } bind Retrofit::class

}