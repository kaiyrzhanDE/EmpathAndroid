package kaiyrzhan.de.core.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module

private const val SHARED_PREFERENCES = "shared_preferences"

val sharedPreferencesModule = module {
    single {
        androidApplication().getSharedPreferences(
            SHARED_PREFERENCES,
            Context.MODE_PRIVATE,
        )
    } bind SharedPreferences::class

    single { TokenPreferencesImpl(get<SharedPreferences>()) } bind TokenPreferences::class
}
