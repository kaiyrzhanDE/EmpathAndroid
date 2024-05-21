package kaiyrzhan.de.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kaiyrzhan.de.core.error_dialog.ErrorDialogState
import kaiyrzhan.de.auth.domain.usecase.LoginUseCase
import kaiyrzhan.de.core.network.RequestResult
import kaiyrzhan.de.core.token.usecase.SaveTokenUseCase
import kaiyrzhan.de.utils.logger.Logger
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val logger: Logger,
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
) : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
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
                    if (isValidPassword(screenState.password)) {
                        when (
                            val request = loginUseCase(
                                email = screenState.email,
                                password = screenState.password
                            )
                        ) {
                            is RequestResult.Error -> {
                                _screenStateFlow.value = screenState.copy(
                                    errorDialogState = screenState.errorDialogState.copy(
                                        isVisible = true
                                    )
                                )
                            }

                            is RequestResult.Success -> {
                                saveTokenUseCase(request.data)
                                _actionsFlow.send(LoginAction.OnSuccessLogin)
                            }

                            else -> Unit
                        }
                    } else {
                        _screenStateFlow.value = screenState.copy(
                            isPasswordTipsDialogVisible = true,
                        )
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

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern =
            Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,128}$")
        return passwordPattern.matcher(password).matches()
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


