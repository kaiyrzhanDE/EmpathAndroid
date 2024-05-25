package kaiyrzhan.de.empath.app

import android.app.Application
import kaiyrzhan.de.auth.authApiModule
import kaiyrzhan.de.core.network.networkModule
import kaiyrzhan.de.core.shared_preferences.sharedPreferencesModule
import kaiyrzhan.de.core.token.tokenModule
import kaiyrzhan.de.utils.dispatcher.dispatchersModule
import kaiyrzhan.de.utils.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                tokenModule,
                sharedPreferencesModule,
                networkModule,
                authApiModule,
                utilsModule,
                dispatchersModule,
            )
        }
    }

}