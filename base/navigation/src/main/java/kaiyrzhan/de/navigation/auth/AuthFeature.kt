package kaiyrzhan.de.navigation.auth

import cafe.adriel.voyager.core.registry.ScreenProvider
import kaiyrzhan.de.navigation.auth.model.ToolbarState

sealed class AuthFeature : ScreenProvider {
    data object LoginScreen: AuthFeature()
    data object PrivacyScreen: AuthFeature()
    data class EntryEmailScreen(val state: ToolbarState): AuthFeature()
    data class EntryCodeScreen(val state: ToolbarState) : AuthFeature()
    data object ResetPasswordScreen: AuthFeature()
    data object CreateAccountScreen: AuthFeature()
}