package kaiyrzhan.de.core.token.usecase

import kaiyrzhan.de.core.shared_preferences.TokenPreferences
import kaiyrzhan.de.core.token.models.Token

class SaveTokenUseCase constructor(
    private val preferences: TokenPreferences,
) {
    operator fun invoke(token: Token) = preferences.saveToken(token)
}