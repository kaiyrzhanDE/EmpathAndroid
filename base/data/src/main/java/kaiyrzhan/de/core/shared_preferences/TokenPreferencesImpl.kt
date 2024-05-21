package kaiyrzhan.de.core.shared_preferences

import android.content.SharedPreferences
import kaiyrzhan.de.core.token.models.Token
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TokenPreferencesImpl(
    private val sharedPreferences: SharedPreferences,
) : TokenPreferences {

    override fun saveToken(token: Token) {
        sharedPreferences
            .edit()
            .putString(TOKEN, Json.encodeToString(token))
            .apply()
    }

    override fun deleteToken(): Boolean {
        return sharedPreferences
            .edit()
            .remove(TOKEN)
            .commit()
    }

    override fun getToken(): Token? {
        val token = sharedPreferences
            .getString(TOKEN, null)
        return if (token.isNullOrEmpty()) null else Json.decodeFromString<Token>(token)
    }

    companion object {
        private const val TOKEN = "token"
    }
}