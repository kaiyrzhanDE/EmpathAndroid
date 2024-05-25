package kaiyrzhan.de.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import kaiyrzhan.de.auth.root_navigation.AuthContent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(component: RootComponent, modifier: Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = predictiveBackAnimation(
            backHandler = component.backHandler,
            onBack = component::onBackClicked,
            animation = stackAnimation(slide()),
        ),
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (val child = it.instance) {
                is RootComponent.Child.Auth -> AuthContent(
                    child.component, Modifier.fillMaxSize())
            }
        }
    }
}