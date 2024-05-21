package kaiyrzhan.de.auth.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import kaiyrzhan.de.auth.domain.models.CreateUser
import kaiyrzhan.de.auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(query: CreateUser) = repository.registerUser(query)
}