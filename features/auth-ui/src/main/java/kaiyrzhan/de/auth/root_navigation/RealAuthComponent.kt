package kaiyrzhan.de.auth.root_navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popWhile
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kaiyrzhan.de.auth.login.RealLoginComponent
import kaiyrzhan.de.auth.privacy.RealPrivacyComponent
import kaiyrzhan.de.auth.registration.create_account.RealCreateAccountComponent
import kaiyrzhan.de.auth.registration.entry_code.RealEntryCodeComponent
import kaiyrzhan.de.auth.registration.entry_email.RealEntryEmailComponent
import kaiyrzhan.de.auth.registration.reset_password.RealResetPasswordComponent
import kaiyrzhan.de.auth.model.ToolbarState
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

class RealAuthComponent(
    componentContext: ComponentContext,
    private val coroutineContext: CoroutineContext,
) : ComponentContext by componentContext, AuthComponent {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, AuthComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Login,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createChild,
    )

    override fun onBackClicked() = navigation.pop()

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): AuthComponent.Child =
        when (config) {
            is Config.Login -> AuthComponent.Child.Login(
                RealLoginComponent(
                    componentContext = componentContext,
                    coroutineContext = coroutineContext,
                    onResetPasswordChosen = {
                        navigation.push(Config.EntryEmail(ToolbarState.RESET_PASSWORD))
                    },
                    onRegistrationChosen = {
                        navigation.push(Config.EntryEmail(ToolbarState.REGISTRATION))
                    },
                    onPrivacyChosen = {
                        navigation.push(Config.Privacy)
                    },
                    onLoginSuccess = {
                        //TODO(Navigate to main screen)
                    }
                )
            )

            is Config.CreateAccount -> AuthComponent.Child.CreateAccount(
                RealCreateAccountComponent(
                    componentContext = componentContext,
                    coroutineContext = coroutineContext,
                    onBackChosen = {
                        navigation.popWhile { topOfStack ->
                            topOfStack !is Config.EntryEmail
                        }
                    },
                )
            )

            is Config.Privacy -> AuthComponent.Child.Privacy(
                RealPrivacyComponent(componentContext)
            )

            is Config.EntryCode -> AuthComponent.Child.EntryCode(
                RealEntryCodeComponent(
                    componentContext = componentContext,
                    coroutineContext = coroutineContext,
                    toolbarState = config.toolbarState,
                    onBackChosen = ::onBackClicked,
                    onRegistrationChosen = {
                        navigation.push(Config.CreateAccount)
                    },
                    onResetPasswordChosen = {
                        navigation.push(Config.ResetPassword)
                    },
                )
            )

            is Config.EntryEmail -> AuthComponent.Child.EntryEmail(
                RealEntryEmailComponent(
                    componentContext = componentContext,
                    coroutineContext = coroutineContext,
                    toolbarState = config.toolbarState,
                    onBackChosen = ::onBackClicked,
                    onSendEmailChosen = {
                        navigation.push(Config.EntryCode(config.toolbarState))
                    },
                )
            )

            is Config.ResetPassword -> AuthComponent.Child.ResetPassword(
                RealResetPasswordComponent(
                    componentContext = componentContext,
                    coroutineContext = coroutineContext,
                    onBackChosen = {
                        navigation.popWhile { topOfStack ->
                            topOfStack !is Config.EntryEmail
                        }
                    },
                )
            )
        }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Login : Config

        @Serializable
        data object Privacy : Config

        @Serializable
        data object CreateAccount : Config

        @Serializable
        data class EntryCode(val toolbarState: ToolbarState) : Config

        @Serializable
        data class EntryEmail(val toolbarState: ToolbarState) : Config

        @Serializable
        data object ResetPassword : Config
    }
}
