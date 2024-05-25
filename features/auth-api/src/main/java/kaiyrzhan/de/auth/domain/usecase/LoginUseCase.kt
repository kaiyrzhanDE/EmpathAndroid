package kaiyrzhan.de.auth.domain.usecase

import kaiyrzhan.de.auth.domain.models.Login
import kaiyrzhan.de.auth.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String) =
        repository.loginUser(Login(email, password))
}