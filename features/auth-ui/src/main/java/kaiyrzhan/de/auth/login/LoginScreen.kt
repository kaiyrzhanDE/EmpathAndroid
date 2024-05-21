package kaiyrzhan.de.auth.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kaiyrzhan.de.core.error_dialog.ErrorDialog
import kaiyrzhan.de.auth.login.dialog.PasswordTipsDialog
import kaiyrzhan.de.core.effects.SingleActionEffect
import kaiyrzhan.de.navigation.auth.AuthFeature
import kaiyrzhan.de.navigation.auth.model.ToolbarState
import javax.inject.Inject

internal class LoginScreen @Inject constructor() : Screen {

    companion object {
        const val TAG = "LoginScreen"
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val privacyScreen = rememberScreen(AuthFeature.PrivacyScreen)
        val registrationScreen = rememberScreen(
            AuthFeature.EntryEmailScreen(ToolbarState.REGISTRATION)
        )
        val resetPasswordScreen = rememberScreen(
            AuthFeature.EntryEmailScreen(ToolbarState.RESET_PASSWORD)
        )

        val viewModel: LoginViewModel = hiltViewModel()
        val screenState = viewModel.screenStateFlow.collectAsState()

        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

        SingleActionEffect(sideEffectFlow = viewModel.actionsFlow) { action ->
            when (action) {
                is LoginAction.OnSuccessLogin -> {
                    //TODO navigate to main screen
                }
            }
        }

        when (val state = screenState.value) {
            is LoginState.None -> Unit

            is LoginState.Loading -> {
                //TODO Loading Animation
            }

            is LoginState.Login -> {


                if (state.errorDialogState.isVisible) {
                    ErrorDialog(
                        iconResId = state.errorDialogState.iconResId,
                        titleResId = state.errorDialogState.titleResId,
                        description = state.errorDialogState.description,
                        onDismissRequest = {
                            viewModel.onEvent(LoginEvent.ShowErrorDialog(false))
                        },
                    )
                }

                if (state.isPasswordTipsDialogVisible) {
                    PasswordTipsDialog(
                        sheetState = sheetState,
                        onDismissRequest = {
                            viewModel.onEvent(LoginEvent.ShowPasswordTipsDialog(false))
                        }
                    )
                }

                LoginContent(
                    username = state.email,
                    onUsernameChange = { username ->
                        viewModel.onEvent(
                            LoginEvent.OnUserNameChanged(username)
                        )
                    },
                    password = state.password,
                    onPasswordChange = { password ->
                        viewModel.onEvent(
                            LoginEvent.OnPasswordChanged(password)
                        )
                    },
                    onGoogleAuthClicked = {
                        //TODO(Implement on the future)
                    },
                    onFacebookAuthClicked = {
                        //TODO(Implement on the future)
                    },
                    onLoginClicked = {
                        viewModel.onEvent(LoginEvent.OnLoginClicked)
                    },
                    onRegistrationClicked = {
                        navigator.push(registrationScreen)
                    },
                    onPrivacyClicked = {
                        navigator.push(privacyScreen)
                    },
                    onResetPasswordClicked = {
                        navigator.push(resetPasswordScreen)
                    }
                )
            }
        }
    }

}
