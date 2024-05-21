package kaiyrzhan.de.auth.registration.create_account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kaiyrzhan.de.core.permission.PermissionDialog
import kaiyrzhan.de.feature_auth.R

internal class CreateAccountScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: CreateAccountViewModel = hiltViewModel()
        val navigator = LocalNavigator.currentOrThrow

        val screenState = viewModel.screenStateFlow.collectAsState()

        when (val state = screenState.value) {
            is CreateAccountState.None -> {}
            is CreateAccountState.Loading -> {
                //TODO Loading Animation
            }

            is CreateAccountState.CreateAccount -> {
                CreateAccountContent(
                    nickname = state.nickname,
                    onNicknameChange = { nickname ->
                        viewModel.onEvent(
                            CreateAccountEvent.OnNicknameChanged(nickname)
                        )
                    },
                    password = state.password,
                    isPasswordVisible = state.isPasswordVisible,
                    onPasswordChange = { password ->
                        viewModel.onEvent(
                            CreateAccountEvent.OnPasswordChanged(password)
                        )
                    },
                    onPasswordShowClicked = {
                        viewModel.onEvent(CreateAccountEvent.ShowPassword)
                    },
                    repeatedPassword = state.repeatedPassword,
                    isRepeatedPasswordVisible = state.isRepeatedPasswordVisible,
                    onRepeatedPasswordChange = { repeatedPassword ->
                        viewModel.onEvent(
                            CreateAccountEvent.OnRepeatedPasswordChanged(repeatedPassword)
                        )
                    },
                    onRepeatedPasswordShowClicked = {
                        viewModel.onEvent(CreateAccountEvent.ShowRepeatedPassword)
                    },
                    onCreateAccountClicked = {
                        viewModel.onEvent(CreateAccountEvent.CreateAccountClicked)
                    },
                    onBackClicked = {
                        navigator.pop()
                    }
                )
            }
        }
    }
}