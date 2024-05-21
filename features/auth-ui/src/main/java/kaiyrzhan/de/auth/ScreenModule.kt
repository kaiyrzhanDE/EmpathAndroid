package kaiyrzhan.de.auth

import cafe.adriel.voyager.core.registry.screenModule
import kaiyrzhan.de.auth.login.LoginScreen
import kaiyrzhan.de.auth.privacy.PrivacyScreen
import kaiyrzhan.de.auth.registration.create_account.CreateAccountScreen
import kaiyrzhan.de.auth.registration.entry_email.EntryEmailScreen
import kaiyrzhan.de.auth.registration.entry_code.EntryCodeScreen
import kaiyrzhan.de.auth.registration.reset_password.ResetPasswordScreen
import kaiyrzhan.de.navigation.auth.AuthFeature

val featureAuthScreenModule = screenModule {
    register<AuthFeature.LoginScreen> {_ ->
        LoginScreen()
    }

    register<AuthFeature.EntryEmailScreen> { provider ->
        EntryEmailScreen(provider.state)
    }

    register<AuthFeature.EntryCodeScreen> { provider ->
        EntryCodeScreen(provider.state)
    }

    register<AuthFeature.PrivacyScreen> { _ ->
        PrivacyScreen()
    }

    register<AuthFeature.CreateAccountScreen> {_ ->
        CreateAccountScreen()
    }

    register<AuthFeature.ResetPasswordScreen> {_ ->
        ResetPasswordScreen()
    }
}