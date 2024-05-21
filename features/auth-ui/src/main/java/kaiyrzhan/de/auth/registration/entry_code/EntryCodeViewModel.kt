package kaiyrzhan.de.auth.registration.entry_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.security.KeyStore.Entry
import javax.inject.Inject

@HiltViewModel
internal class EntryCodeViewModel @Inject constructor(

) : ViewModel() {
    private val _screenStateFlow = MutableStateFlow(EntryCodeState.EntryCode())
    val screenStateFlow: StateFlow<EntryCodeState> get() = _screenStateFlow.asStateFlow()

    private val _actionsFlow = Channel<EntryCodeAction>(capacity = Channel.BUFFERED)
    val actionsFlow: Flow<EntryCodeAction> get() = _actionsFlow.receiveAsFlow()

    fun onEvent(event: EntryCodeEvent) {
        viewModelScope.launch {
            val screenState = _screenStateFlow.value as? EntryCodeState.EntryCode ?: return@launch
            when (event) {
                is EntryCodeEvent.OnCodeChange -> {
                    _screenStateFlow.value = screenState.copy(
                        code = event.text,
                    )
                }

                is EntryCodeEvent.OnRemoveCodeClicked -> {
                    _screenStateFlow.value = screenState.copy(
                        code = screenState.code.dropLast(1)
                    )
                }

                is EntryCodeEvent.OnCheckCodeClicked -> {
                    //TODO request to check code
                    _actionsFlow.send(EntryCodeAction.NavigateToNextScreen)
                }
            }
        }
    }

}

internal sealed class EntryCodeState {
    data object None : EntryCodeState()
    data object Loading : EntryCodeState()
    data class EntryCode(
        val code: String = "",
    ) : EntryCodeState()
}

internal sealed class EntryCodeEvent {
    data class OnCodeChange(val text: String) : EntryCodeEvent()
    data object OnRemoveCodeClicked : EntryCodeEvent()
    data object OnCheckCodeClicked : EntryCodeEvent()
}

internal sealed class EntryCodeAction {
    data object NavigateToNextScreen : EntryCodeAction()
}