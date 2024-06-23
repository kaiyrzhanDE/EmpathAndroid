package kaiyrzhan.de.auth.root_navigation

import android.util.Log
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
import kaiyrzhan.de.auth.registration.optional_info.RealOptionalComponent
import kotlinx.serialization.Serializable

class RealAuthComponent(
    componentContext: ComponentContext,
    private val onSuccessAuthentication: () -> Unit,
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
        componentContext: ComponentContext,
    ): AuthComponent.Child =
        when (config) {
            is Config.Login -> AuthComponent.Child.Login(
                RealLoginComponent(
                    componentContext = componentContext,
                    onResetPasswordChosen = {
                        navigation.push(Config.EntryEmail(ToolbarState.RESET_PASSWORD))
                    },
                    onRegistrationChosen = {
                        navigation.push(Config.EntryEmail(ToolbarState.REGISTRATION))
                    },
                    onPrivacyChosen = {
                        navigation.push(Config.Privacy)
                    },
                    onLoginSuccess = onSuccessAuthentication,
                )
            )

            is Config.CreateAccount -> AuthComponent.Child.CreateAccount(
                RealCreateAccountComponent(
                    componentContext = componentContext,
                    onCreateAccountChosen = {
                        navigation.push(Config.Optional)
                    },
                    onBackChosen = {
                        navigation.popWhile { topOfStack ->
                            topOfStack !is Config.EntryEmail
                        }
                    },
                )
            )

            is Config.Privacy -> AuthComponent.Child.Privacy(
                RealPrivacyComponent(
                    componentContext = componentContext,
                    onBackChosen = ::onBackClicked,
                )
            )

            is Config.EntryCode -> AuthComponent.Child.EntryCode(
                RealEntryCodeComponent(
                    componentContext = componentContext,
                    toolbarState = config.toolbarState,
                    email = config.email,
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
                    toolbarState = config.toolbarState,
                    onBackChosen = ::onBackClicked,
                    onSendEmailChosen = { email ->
                        navigation.push(Config.EntryCode(config.toolbarState, email))
                    },
                    onResetPasswordChosen = {
                        navigation.push(Config.ResetPassword)
                    },
                )
            )

            is Config.ResetPassword -> AuthComponent.Child.ResetPassword(
                RealResetPasswordComponent(
                    componentContext = componentContext,
                    onBackChosen = {
                        navigation.popWhile { topOfStack ->
                            topOfStack !is Config.EntryEmail
                        }
                    },
                )
            )

            is Config.Optional -> AuthComponent.Child.Optional(
                RealOptionalComponent(componentContext)
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
        data class EntryCode(
            val toolbarState: ToolbarState,
            val email: String,
        ) : Config

        @Serializable
        data class EntryEmail(val toolbarState: ToolbarState) : Config

        @Serializable
        data object ResetPassword : Config

        @Serializable
        data object Optional : Config
    }
}
