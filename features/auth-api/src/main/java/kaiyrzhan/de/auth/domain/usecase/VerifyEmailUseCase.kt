package kaiyrzhan.de.auth.domain.usecase

import kaiyrzhan.de.auth.domain.models.VerifyEmail
import kaiyrzhan.de.auth.domain.repository.AuthRepository

class VerifyEmailUseCase(
    private val repository: AuthRepository,
) {
    suspend fun invoke(email: String, code: String) =
        repository.verifyEmail(VerifyEmail(email, code))
}