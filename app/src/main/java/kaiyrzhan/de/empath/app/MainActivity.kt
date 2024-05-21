package kaiyrzhan.de.empath.app

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kaiyrzhan.de.auth.login.LoginScreen
import kaiyrzhan.de.core.theme.ApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

         setContent {
             LaunchedEffect(true) {
                 WindowCompat.setDecorFitsSystemWindows(window, false)
             }
            ApplicationTheme {
                Navigator(LoginScreen())
            }
        }
    }

}


