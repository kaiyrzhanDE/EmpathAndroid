package kaiyrzhan.de.auth.registration.entry_code

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kaiyrzhan.de.core.effects.SingleActionEffect
import kaiyrzhan.de.navigation.auth.AuthFeature
import kaiyrzhan.de.navigation.auth.model.ToolbarState

internal class EntryCodeScreen(
    private val toolbarState: ToolbarState,
) : Screen {


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val nextScreen = rememberScreen(
            when (toolbarState) {
                ToolbarState.RESET_PASSWORD -> AuthFeature.ResetPasswordScreen
                ToolbarState.REGISTRATION -> AuthFeature.CreateAccountScreen
            }
        )

        val viewModel: EntryCodeViewModel = hiltViewModel()
        val screenState = viewModel.screenStateFlow.collectAsState()

        SingleActionEffect(sideEffectFlow = viewModel.actionsFlow) { action ->
            when (action) {
                is EntryCodeAction.NavigateToNextScreen -> {
                    navigator.push(nextScreen)
                }
            }
        }

        when (val state = screenState.value) {
            is EntryCodeState.None -> {}
            is EntryCodeState.Loading -> {
                //TODO Loading Animation
            }

            is EntryCodeState.EntryCode -> {
                EntryCodeContent(
                    toolbarState = toolbarState,
                    code = state.code,
                    onCodeChange = { code ->
                        viewModel.onEvent(
                            EntryCodeEvent.OnCodeChange(code)
                        )
                    },
                    onRemoveClicked = {
                        viewModel.onEvent(EntryCodeEvent.OnRemoveCodeClicked)
                    },
                    onSendCodeClicked = {
                        viewModel.onEvent(EntryCodeEvent.OnCheckCodeClicked)
                    },
                    onBackClicked = {
                        navigator.pop()
                    },
                )

            }
        }
    }
}