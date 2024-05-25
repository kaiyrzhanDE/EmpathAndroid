package kaiyrzhan.de.auth.registration.create_account

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kaiyrzhan.de.auth.registration.create_account.model.CreateAccountState
import kaiyrzhan.de.auth.root_navigation.AuthComponent
import kotlinx.coroutines.flow.StateFlow

interface CreateAccountComponent: BackHandlerOwner {
    val screenStateFlow: StateFlow<CreateAccountState>

    fun onNicknameChanged(nickname: String?)
    fun onPasswordChanged(password: String?)
    fun onRepeatedPasswordChanged(password: String?)

    fun onPasswordShowClicked()
    fun onRepeatedPasswordShowClicked()
    fun onCreateAccountClicked()

    fun onBackClicked()
}