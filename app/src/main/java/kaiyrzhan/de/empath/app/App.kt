package kaiyrzhan.de.empath.app

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import dagger.hilt.android.HiltAndroidApp
import kaiyrzhan.de.auth.featureAuthScreenModule

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ScreenRegistry {
            featureAuthScreenModule()
        }
    }
}