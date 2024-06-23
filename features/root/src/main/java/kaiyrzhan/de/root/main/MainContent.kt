package kaiyrzhan.de.root.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import kaiyrzhan.de.core.animations.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import kaiyrzhan.de.auth.login.LoginContent
import kaiyrzhan.de.root.main.profile.ProfileContent

@Composable
fun MainContent(component: MainComponent, modifier: Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(slide() + fade()),
    ) {
        when (val child = it.instance) {
            is MainComponent.Child.Profile -> ProfileContent(component = child.component)
        }
    }
}

