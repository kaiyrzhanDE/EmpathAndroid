package kaiyrzhan.de.root.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kaiyrzhan.de.root.main.profile.RealProfileComponent
import kotlinx.serialization.Serializable

class RealMainComponent(
    componentContext: ComponentContext,
    private val onBackChosen: () -> Unit,
) : ComponentContext by componentContext, MainComponent {
    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, MainComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialStack = { listOf(Config.Profile) },
            handleBackButton = true,
            childFactory = ::createChild
        )

    override fun onBackClicked() = navigation.pop()

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): MainComponent.Child =
        when (config) {
            is Config.Profile -> MainComponent.Child.Profile(
                RealProfileComponent(
                    componentContext = componentContext,
                    onBackChosen = onBackChosen,
                )
            )
        }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Profile : Config

    }
}
