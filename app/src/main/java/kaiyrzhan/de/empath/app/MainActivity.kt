package kaiyrzhan.de.empath.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import kaiyrzhan.de.core.theme.ApplicationTheme
import kaiyrzhan.de.root.DefaultRootComponent
import kaiyrzhan.de.root.RootContent
import kaiyrzhan.de.utils.dispatcher.AppDispatchers
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val componentContext by lazy { defaultComponentContext() }
    private val appDispatchers: AppDispatchers by inject()

    private val component by lazy { DefaultRootComponent(componentContext, appDispatchers.main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            LaunchedEffect(Unit) {
                WindowCompat.setDecorFitsSystemWindows(window, false)
            }
            ApplicationTheme{ RootContent(component, Modifier.fillMaxSize()) }
        }
    }
}


