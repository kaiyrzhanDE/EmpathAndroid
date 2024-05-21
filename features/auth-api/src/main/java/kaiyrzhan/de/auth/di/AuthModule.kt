package kaiyrzhan.de.auth.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kaiyrzhan.de.auth.data.network.AuthApi
import kaiyrzhan.de.auth.data.repository.AuthRepositoryImpl
import kaiyrzhan.de.auth.domain.repository.AuthRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AuthModule {

    @Singleton
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    companion object{
        @Singleton
        @Provides
        fun provideAuthApi(retrofit: Retrofit) : AuthApi {
            return retrofit.create(AuthApi::class.java)
        }
    }


}