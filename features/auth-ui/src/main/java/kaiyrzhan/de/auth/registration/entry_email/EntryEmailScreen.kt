package kaiyrzhan.de.auth.registration.entry_email

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

internal class EntryEmailScreen(
    private val toolbarState: ToolbarState
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val entryCodeScreen = rememberScreen(AuthFeature.EntryCodeScreen(toolbarState))

        val viewModel: EntryEmailScreenModel = hiltViewModel()
        val screenState = viewModel.screenStateFlow.collectAsState()

        SingleActionEffect(sideEffectFlow = viewModel.actionsFlow) { action ->
            when (action) {
                is EntryEmailAction.NavigateToEntryCodeScreen -> {
                    navigator.push(entryCodeScreen)
                }
            }

        }

        when (val state = screenState.value) {
            is EntryEmailState.None -> {}
            is EntryEmailState.Loading -> {
                //TODO Loading Animation
            }

            is EntryEmailState.EntryEmail -> {
                EntryEmailContent(
                    state = toolbarState,
                    onBackClicked = {
                        navigator.pop()
                    },
                    onSendEmailClicked = {
                        viewModel.onEvent(
                            EntryEmailEvent.OnSendCodeClicked
                        )
                    },
                    email = state.email,
                    onEmailChange = { email ->
                        viewModel.onEvent(
                            EntryEmailEvent.OnEmailChanged(email)
                        )
                    }
                )
            }
        }
    }

}
