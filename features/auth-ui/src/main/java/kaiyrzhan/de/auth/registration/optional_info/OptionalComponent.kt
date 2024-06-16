package kaiyrzhan.de.auth.registration.optional_info

import android.net.Uri
import kaiyrzhan.de.auth.registration.optional_info.model.OptionalState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

interface OptionalComponent {
    val screenStateFlow: StateFlow<OptionalState>

    fun onFirstNameChanged(firstName: String?)
    fun onLastNameChanged(lastName: String?)

    fun onGenderSelected(gender: String)
    fun onDateOfBirthSelected(date: Date)
    fun onUserImageSelected(uri: Uri?)

    fun onSkipClicked()
    fun onSaveClicked()
}