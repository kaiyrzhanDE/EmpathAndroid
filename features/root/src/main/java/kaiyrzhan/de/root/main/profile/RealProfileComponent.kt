package kaiyrzhan.de.root.main.profile

import com.arkivanov.decompose.ComponentContext

class RealProfileComponent(
    componentContext: ComponentContext,
    private val onBackChosen: () -> Unit,
) : ComponentContext by componentContext, ProfileComponent {
    override fun onBackClicked() = onBackChosen()
}