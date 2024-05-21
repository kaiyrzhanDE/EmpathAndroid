package kaiyrzhan.de.auth.registration.reset_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ResetPasswordViewModel @Inject constructor(

) : ViewModel() {
    private val _screenStateFlow = MutableStateFlow(ResetPasswordState.ResetPassword())
    val screenStateFlow: StateFlow<ResetPasswordState> get() = _screenStateFlow.asStateFlow()

    fun onEvent(event: ResetPasswordEvent){
        viewModelScope.launch {
            val screenState = _screenStateFlow.value as? ResetPasswordState.ResetPassword ?: return@launch

            when(event){
                is ResetPasswordEvent.OnPasswordChanged -> {
                    _screenStateFlow.value = screenState.copy(
                        password = event.text
                    )
                }
                is ResetPasswordEvent.OnRepeatedPasswordChanged -> {
                    _screenStateFlow.value = screenState.copy(
                        repeatedPassword = event.text
                    )
                }
                is ResetPasswordEvent.ShowPassword ->{
                    _screenStateFlow.value = screenState.copy(
                        isPasswordVisible = !screenState.isPasswordVisible
                    )
                }
                is ResetPasswordEvent.ShowRepeatedPassword ->{
                    _screenStateFlow.value = screenState.copy(
                        isRepeatedPasswordVisible = !screenState.isRepeatedPasswordVisible
                    )
                }
                is ResetPasswordEvent.ResetPasswordClicked -> {
                    //TODO request to creating account
                }
            }
        }
    }

}


sealed class ResetPasswordState{
    data object None: ResetPasswordState()
    data object Loading: ResetPasswordState()
    data class ResetPassword(
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val repeatedPassword: String = "",
        val isRepeatedPasswordVisible: Boolean = false,
    ): ResetPasswordState()
}

sealed class ResetPasswordEvent{
    data class OnPasswordChanged(val text: String): ResetPasswordEvent()
    data class OnRepeatedPasswordChanged(val text: String): ResetPasswordEvent()

    data object ShowPassword: ResetPasswordEvent()
    data object ShowRepeatedPassword: ResetPasswordEvent()

    data object ResetPasswordClicked: ResetPasswordEvent()
}