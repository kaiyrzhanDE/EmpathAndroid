package kaiyrzhan.de.auth.privacy

import com.arkivanov.decompose.ComponentContext

class RealPrivacyComponent(
    componentContext: ComponentContext,
    private val onBackChosen: () -> Unit,
): ComponentContext by componentContext, PrivacyComponent {
    override fun onBackClicked() = onBackChosen()
}