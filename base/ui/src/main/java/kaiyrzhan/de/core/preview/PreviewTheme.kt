package kaiyrzhan.de.core.preview

import androidx.compose.runtime.Composable
import kaiyrzhan.de.core.theme.ApplicationTheme

@Composable
fun PreviewTheme(content: @Composable () -> Unit){
    ApplicationTheme {
        content()
    }
}