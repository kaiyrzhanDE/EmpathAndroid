package kaiyrzhan.de.auth.domain.usecase

import kaiyrzhan.de.auth.domain.models.Login
import kaiyrzhan.de.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String) =
        repository.loginUser(Login(email, password))
}