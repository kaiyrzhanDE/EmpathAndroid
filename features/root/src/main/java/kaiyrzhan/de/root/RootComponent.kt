package kaiyrzhan.de.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandler
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kaiyrzhan.de.auth.root_navigation.AuthComponent
import kaiyrzhan.de.root.main.MainComponent

interface RootComponent: BackHandlerOwner {

    val stack:Value<ChildStack<*, Child>>

    fun onBackClicked()

    sealed interface Child{
        data class Auth(val component: AuthComponent): Child
        data class Main(val component: MainComponent): Child
    }
}