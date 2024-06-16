package kaiyrzhan.de.auth.registration.optional_info

import android.net.Uri
import kaiyrzhan.de.auth.registration.optional_info.model.OptionalState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

class FakeOptionalComponent: OptionalComponent {
    override val screenStateFlow = MutableStateFlow(OptionalState.CreateAccount())

    override fun onFirstNameChanged(firstName: String?) = Unit
    override fun onLastNameChanged(lastName: String?) = Unit

    override fun onGenderSelected(gender: String) = Unit
    override fun onDateOfBirthSelected(date: Date) = Unit
    override fun onUserImageSelected(uri: Uri?) = Unit

    override fun onSkipClicked() = Unit
    override fun onSaveClicked() = Unit
}