package kaiyrzhan.de.auth.registration.entry_email

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
import javax.inject.Inject

@HiltViewModel
internal class EntryEmailScreenModel @Inject constructor(

) : ViewModel() {

    private val _screenStateFlow = MutableStateFlow(EntryEmailState.EntryEmail())
    val screenStateFlow: StateFlow<EntryEmailState> get() = _screenStateFlow.asStateFlow()

    private val _actionsFlow = Channel<EntryEmailAction>(capacity = Channel.BUFFERED)
    val actionsFlow: Flow<EntryEmailAction> get() = _actionsFlow.receiveAsFlow()

    fun onEvent(event: EntryEmailEvent) {
        viewModelScope.launch {
            val screenState = _screenStateFlow.value as? EntryEmailState.EntryEmail ?: return@launch
            when (event) {
                is EntryEmailEvent.OnEmailChanged -> {
                    _screenStateFlow.value = screenState.copy(
                        email = event.text,
                    )
                }

                is EntryEmailEvent.OnSendCodeClicked -> {
                    //TODO send code request
                    _actionsFlow.send(EntryEmailAction.NavigateToEntryCodeScreen)
                }
            }
        }
    }

}

sealed class EntryEmailState {
    data object None : EntryEmailState()
    data object Loading : EntryEmailState()
    data class EntryEmail(val email: String = "") : EntryEmailState()
}

sealed class EntryEmailEvent {
    data class OnEmailChanged(val text: String) : EntryEmailEvent()
    data object OnSendCodeClicked : EntryEmailEvent()
}

sealed class EntryEmailAction {
    data object NavigateToEntryCodeScreen: EntryEmailAction()
}
