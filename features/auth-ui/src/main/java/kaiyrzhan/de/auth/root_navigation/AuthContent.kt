package kaiyrzhan.de.auth.root_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import kaiyrzhan.de.auth.login.LoginContent
import kaiyrzhan.de.auth.privacy.PrivacyContent
import kaiyrzhan.de.auth.registration.create_account.CreateAccountContent
import kaiyrzhan.de.auth.registration.entry_code.EntryCodeContent
import kaiyrzhan.de.auth.registration.entry_email.EntryEmailContent
import kaiyrzhan.de.auth.registration.optional_info.OptionalContent
import kaiyrzhan.de.auth.registration.reset_password.ResetPasswordContent
import kaiyrzhan.de.core.animations.slide

@Composable
fun AuthContent(component: AuthComponent, modifier: Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(slide() + fade()),
    ) {
        when (val child = it.instance) {
            is AuthComponent.Child.Login -> LoginContent(component = child.component)
            is AuthComponent.Child.CreateAccount -> CreateAccountContent(component = child.component)
            is AuthComponent.Child.Privacy -> PrivacyContent(component = child.component)
            is AuthComponent.Child.EntryCode -> EntryCodeContent(component = child.component)
            is AuthComponent.Child.EntryEmail -> EntryEmailContent(component = child.component)
            is AuthComponent.Child.ResetPassword -> ResetPasswordContent(component = child.component)
            is AuthComponent.Child.Optional -> OptionalContent(component = child.component)
        }
    }
}