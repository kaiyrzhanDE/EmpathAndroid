package kaiyrzhan.de.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import kaiyrzhan.de.auth.root_navigation.RealAuthComponent
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val coroutineContext: CoroutineContext,
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialStack = { listOf(Config.Auth) },
            handleBackButton = true,
            childFactory = ::createChild
        )

    override fun onBackClicked() { navigation.pop() }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (config) {
            is Config.Auth -> RootComponent.Child.Auth(
                RealAuthComponent(
                    componentContext = componentContext,
                    coroutineContext = coroutineContext,
                )
            )
        }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Auth : Config

    }
}