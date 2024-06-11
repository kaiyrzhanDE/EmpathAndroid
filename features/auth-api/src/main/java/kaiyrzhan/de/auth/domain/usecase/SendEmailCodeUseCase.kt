package kaiyrzhan.de.auth.domain.usecase

import kaiyrzhan.de.auth.domain.repository.AuthRepository

class SendEmailCodeUseCase(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(email: String) = repository.sendEmailCode(email)
}