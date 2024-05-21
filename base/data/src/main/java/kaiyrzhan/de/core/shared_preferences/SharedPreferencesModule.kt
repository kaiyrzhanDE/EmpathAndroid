package kaiyrzhan.de.core.shared_preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    private const val SHARED_PREFERENCES = "shared_preferences"

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideTokenPreferences(
        sharedPreferences: SharedPreferences,
    ): TokenPreferences {
        return TokenPreferencesImpl(sharedPreferences)
    }

}
