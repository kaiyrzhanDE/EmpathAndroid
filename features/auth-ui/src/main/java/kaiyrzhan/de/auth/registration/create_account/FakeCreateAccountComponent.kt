package kaiyrzhan.de.auth.registration.create_account

import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.backhandler.BackHandler
import kaiyrzhan.de.auth.registration.create_account.model.CreateAccountState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeCreateAccountComponent: CreateAccountComponent {
    override val backHandler: BackHandler = object: BackHandler{
        override fun register(callback: BackCallback) = Unit
        override fun unregister(callback: BackCallback) = Unit
    }

    override val screenStateFlow = MutableStateFlow(CreateAccountState.CreateAccount())

    override fun onNicknameChanged(nickname: String?) = Unit
    override fun onPasswordChanged(password: String?) = Unit
    override fun onRepeatedPasswordChanged(password: String?) = Unit

    override fun onPasswordShowClicked() = Unit
    override fun onRepeatedPasswordShowClicked() = Unit
    override fun onCreateAccountClicked() = Unit
    override fun onBackClicked() = Unit
}