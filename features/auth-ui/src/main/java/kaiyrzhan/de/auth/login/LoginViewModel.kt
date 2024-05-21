package kaiyrzhan.de.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kaiyrzhan.de.core.error_dialog.ErrorDialogState
import kaiyrzhan.de.auth.domain.usecase.LoginUseCase
import kaiyrzhan.de.core.network.onError
import kaiyrzhan.de.core.network.onException
import kaiyrzhan.de.core.network.onSuccess
import kaiyrzhan.de.core.token.usecase.SaveTokenUseCase
import kaiyrzhan.de.core_ui.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
) : ViewModel() {

    companion object {
        val PasswordRegex =
            Regex("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,128}$")

    }

    private val _actionsFlow = Channel<LoginAction>(capacity = Channel.BUFFERED)
    val actionsFlow: Flow<LoginAction> get() = _actionsFlow.receiveAsFlow()

    private val _screenStateFlow = MutableStateFlow<LoginState>(LoginState.Login())
    val screenStateFlow: StateFlow<LoginState> get() = _screenStateFlow.asStateFlow()

    fun onEvent(event: LoginEvent) {
        viewModelScope.launch {
            val screenState = _screenStateFlow.value as? LoginState.Login ?: return@launch
            when (event) {
                is LoginEvent.OnUserNameChanged -> {
                    _screenStateFlow.value = screenState.copy(
                        email = event.text,
                    )
                }

                is LoginEvent.OnPasswordChanged -> {
                    _screenStateFlow.value = screenState.copy(
                        password = event.text,
                    )
                }

                is LoginEvent.OnLoginClicked -> {
                    if (!PasswordRegex.matches(screenState.password)) {
                        onEvent(LoginEvent.ShowPasswordTipsDialog(true))
                        return@launch
                    }

                    loginUseCase(
                        email = screenState.email,
                        password = screenState.password
                    ).onError { code, _ ->
                        if (code != 422) {
                            _screenStateFlow.value = screenState.copy(
                                errorDialogState = screenState.errorDialogState.copy(
                                    code = code,
                                    isVisible = true
                                )
                            )
                        } else {
                            onEvent(LoginEvent.ShowPasswordTipsDialog(true))
                        }
                    }.onSuccess { token ->
                        saveTokenUseCase(token)
                        _actionsFlow.send(LoginAction.OnSuccessLogin)
                    }
                }

                is LoginEvent.ShowErrorDialog -> {
                    _screenStateFlow.value = screenState.copy(
                        errorDialogState = screenState.errorDialogState.copy(
                            isVisible = event.isVisible,
                        )
                    )
                }

                is LoginEvent.ShowPasswordTipsDialog -> {
                    _screenStateFlow.value = screenState.copy(
                        isPasswordTipsDialogVisible = event.isVisible,
                    )
                }

            }
        }
    }
}

internal sealed class LoginState {
    data object None : LoginState()
    data object Loading : LoginState()
    data class Login(
        val email: String = "",
        val password: String = "",
        val errorDialogState: ErrorDialogState = ErrorDialogState(),
        val isPasswordTipsDialogVisible: Boolean = false,
    ) : LoginState()
}

internal sealed class LoginEvent {
    data class OnPasswordChanged(val text: String) : LoginEvent()
    data class OnUserNameChanged(val text: String) : LoginEvent()
    data object OnLoginClicked : LoginEvent()

    data class ShowErrorDialog(val isVisible: Boolean) : LoginEvent()
    data class ShowPasswordTipsDialog(val isVisible: Boolean) : LoginEvent()
}

internal sealed class LoginAction {
    data object OnSuccessLogin : LoginAction()
}


