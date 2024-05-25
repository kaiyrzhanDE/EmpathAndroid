package kaiyrzhan.de.auth.privacy

import com.arkivanov.decompose.ComponentContext

class RealPrivacyComponent(
    componentContext: ComponentContext,
): ComponentContext by componentContext, PrivacyComponent {

}