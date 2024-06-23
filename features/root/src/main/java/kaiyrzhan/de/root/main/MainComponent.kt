package kaiyrzhan.de.root.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kaiyrzhan.de.root.main.profile.ProfileComponent

interface MainComponent : BackHandlerOwner {
    val stack: Value<ChildStack<*, Child>>
    fun onBackClicked()

    sealed interface Child {
        class Profile(val component: ProfileComponent) : Child
    }
}

