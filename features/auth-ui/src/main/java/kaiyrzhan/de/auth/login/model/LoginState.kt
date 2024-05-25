package kaiyrzhan.de.auth.login.model

import kaiyrzhan.de.core.error_dialog.ErrorDialogState

sealed class LoginState {
    data object None : LoginState()
    data object Loading : LoginState()
    data class Login(
        val email: String = "",
        val password: String = "",
        val errorDialogState: ErrorDialogState = ErrorDialogState(),
        val isPasswordTipsDialogVisible: Boolean = false,
    ) : LoginState()
}