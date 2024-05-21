package kaiyrzhan.de.auth.registration.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CreateAccountViewModel @Inject constructor(

) : ViewModel() {
    private val _screenStateFlow = MutableStateFlow(CreateAccountState.CreateAccount())
    val screenStateFlow: StateFlow<CreateAccountState> get() = _screenStateFlow.asStateFlow()

    fun onEvent(event: CreateAccountEvent){
        viewModelScope.launch {
            val screenState = _screenStateFlow.value as? CreateAccountState.CreateAccount ?: return@launch

            when(event){
                is CreateAccountEvent.OnNicknameChanged -> {
                    _screenStateFlow.value = screenState.copy(
                        nickname = event.text
                    )
                }
                is CreateAccountEvent.OnPasswordChanged -> {
                    _screenStateFlow.value = screenState.copy(
                        password = event.text
                    )
                }
                is CreateAccountEvent.OnRepeatedPasswordChanged -> {
                    _screenStateFlow.value = screenState.copy(
                        repeatedPassword = event.text
                    )
                }
                is CreateAccountEvent.ShowPassword ->{
                    _screenStateFlow.value = screenState.copy(
                        isPasswordVisible = !screenState.isPasswordVisible
                    )
                }
                is CreateAccountEvent.ShowRepeatedPassword ->{
                    _screenStateFlow.value = screenState.copy(
                        isRepeatedPasswordVisible = !screenState.isRepeatedPasswordVisible
                    )
                }
                is CreateAccountEvent.CreateAccountClicked -> {
                    //TODO request to creating account
                }
            }
        }
    }

}


internal sealed class CreateAccountState{
    data object None: CreateAccountState()
    data object Loading: CreateAccountState()
    data class CreateAccount(
        val nickname: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val repeatedPassword: String = "",
        val isRepeatedPasswordVisible: Boolean = false,
    ): CreateAccountState()
}

internal sealed class CreateAccountEvent{
    data class OnNicknameChanged(val text: String): CreateAccountEvent()
    data class OnPasswordChanged(val text: String): CreateAccountEvent()
    data class OnRepeatedPasswordChanged(val text: String): CreateAccountEvent()

    data object ShowPassword: CreateAccountEvent()
    data object ShowRepeatedPassword: CreateAccountEvent()

    data object CreateAccountClicked: CreateAccountEvent()
}