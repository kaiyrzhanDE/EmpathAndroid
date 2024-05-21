package kaiyrzhan.de.auth.registration.reset_password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

internal class ResetPasswordScreen() : Screen {
    @Composable
    override fun Content() {
        val viewModel: ResetPasswordViewModel = hiltViewModel()
        val navigator = LocalNavigator.currentOrThrow

        val screenState = viewModel.screenStateFlow.collectAsState()

        when (val state = screenState.value) {
            is ResetPasswordState.None -> {}
            is ResetPasswordState.Loading -> {
                //TODO Loading Animation
            }

            is ResetPasswordState.ResetPassword -> {
                ResetPasswordContent(
                    password = state.password,
                    isPasswordVisible = state.isPasswordVisible,
                    onPasswordChange = { password ->
                        viewModel.onEvent(
                            ResetPasswordEvent.OnPasswordChanged(password)
                        )
                    },
                    onPasswordShowClicked = {
                        viewModel.onEvent(ResetPasswordEvent.ShowPassword)
                    },
                    repeatedPassword = state.repeatedPassword,
                    isRepeatedPasswordVisible = state.isRepeatedPasswordVisible,
                    onRepeatedPasswordChange = { password ->
                        viewModel.onEvent(ResetPasswordEvent.OnRepeatedPasswordChanged(password))
                    },
                    onRepeatedPasswordShowClicked = {
                        viewModel.onEvent(ResetPasswordEvent.ShowRepeatedPassword)
                    },
                    onResetPasswordClicked = {
                        viewModel.onEvent(ResetPasswordEvent.ResetPasswordClicked)
                    },
                    onBackClicked = {
                        navigator.pop()
                    },
                )
            }
        }
    }
}