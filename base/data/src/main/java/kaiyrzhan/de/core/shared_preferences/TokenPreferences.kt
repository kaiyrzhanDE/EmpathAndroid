package kaiyrzhan.de.core.shared_preferences

import kaiyrzhan.de.core.token.models.Token

interface TokenPreferences{

    fun saveToken(token: Token)

    fun deleteToken(): Boolean

    fun getToken(): Token?
}