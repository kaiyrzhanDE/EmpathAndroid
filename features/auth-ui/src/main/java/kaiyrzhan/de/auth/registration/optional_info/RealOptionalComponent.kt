package kaiyrzhan.de.auth.registration.optional_info

import android.net.Uri
import com.arkivanov.decompose.ComponentContext
import kaiyrzhan.de.auth.registration.optional_info.model.OptionalState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class RealOptionalComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, OptionalComponent {
    override val screenStateFlow = MutableStateFlow(OptionalState.CreateAccount())

    override fun onFirstNameChanged(firstName: String?) =
        screenStateFlow.update { state -> state.copy(firstName = firstName) }

    override fun onLastNameChanged(lastName: String?) =
        screenStateFlow.update { state -> state.copy(lastName = lastName) }

    override fun onGenderSelected(gender: String) =
        screenStateFlow.update { state -> state.copy(gender = gender) }

    override fun onDateOfBirthSelected(date: Date) =
        screenStateFlow.update { state -> state.copy(dateOfBirth = date) }

    override fun onUserImageSelected(uri: Uri?) =
        screenStateFlow.update { state -> state.copy(image = uri) }

    override fun onSkipClicked() {
        TODO("Not yet implemented")
    }
    override fun onSaveClicked() {
        TODO("Not yet implemented")
    }
}