package kaiyrzhan.de.auth.dialog.password_tips_dialog

import com.arkivanov.decompose.ComponentContext

interface PasswordTipsComponent {
    fun onDismissClicked()
}

class RealPasswordTipsComponent(
    componentContext: ComponentContext,
    private val onDismissChosen: () -> Unit,
) : ComponentContext by componentContext, PasswordTipsComponent {
    override fun onDismissClicked() = onDismissChosen()
}

class FakePasswordTipsComponent: PasswordTipsComponent{
    override fun onDismissClicked() = Unit
}