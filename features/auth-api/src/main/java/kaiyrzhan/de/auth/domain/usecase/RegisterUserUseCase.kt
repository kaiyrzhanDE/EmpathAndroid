package kaiyrzhan.de.auth.domain.usecase

import kaiyrzhan.de.auth.domain.models.CreateUser
import kaiyrzhan.de.auth.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(query: CreateUser) = repository.registerUser(query)
}