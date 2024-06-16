package kaiyrzhan.de.auth.registration.optional_info.model

import android.net.Uri
import java.util.Date

sealed class OptionalState {
    data object None: OptionalState()
    data object Loading: OptionalState()
    data class CreateAccount(
        val image: Uri? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val dateOfBirth: Date? = null,
        val gender: String? = null,
    ): OptionalState()
}