package kaiyrzhan.de.auth.root_navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kaiyrzhan.de.auth.login.LoginComponent
import kaiyrzhan.de.auth.privacy.PrivacyComponent
import kaiyrzhan.de.auth.registration.create_account.CreateAccountComponent
import kaiyrzhan.de.auth.registration.entry_code.EntryCodeComponent
import kaiyrzhan.de.auth.registration.entry_email.EntryEmailComponent
import kaiyrzhan.de.auth.registration.optional_info.OptionalComponent
import kaiyrzhan.de.auth.registration.reset_password.ResetPasswordComponent

interface AuthComponent: BackHandlerOwner {
    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked()

    sealed interface Child {
        class Login(val component: LoginComponent) : Child
        class Privacy(val component: PrivacyComponent): Child
        class CreateAccount(val component: CreateAccountComponent) : Child
        class EntryCode(val component: EntryCodeComponent) : Child
        class EntryEmail(val component: EntryEmailComponent) : Child
        class ResetPassword(val component: ResetPasswordComponent) : Child
        class Optional(val component: OptionalComponent) : Child
    }
}


