package kaiyrzhan.de.core.token.usecase

import kaiyrzhan.de.core.shared_preferences.TokenPreferences
import kaiyrzhan.de.core.token.models.Token
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val preferences: TokenPreferences,
) {
    operator fun invoke(token: Token) = preferences.saveToken(token)
}