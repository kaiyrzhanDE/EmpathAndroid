package kaiyrzhan.de.auth.registration.entry_email

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.dialog.MessageDialogComponent
import kaiyrzhan.de.auth.dialog.RealMessageDialogComponent
import kaiyrzhan.de.auth.dialog.MessageDialogConfig
import kaiyrzhan.de.auth.domain.usecase.SendEmailCodeUseCase
import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailState
import kaiyrzhan.de.auth.model.ToolbarState
import kaiyrzhan.de.core.network.onError
import kaiyrzhan.de.core.network.onSuccess
import kaiyrzhan.de.utils.kotlin.STRING_EMPTY
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class RealEntryEmailComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    toolbarState: ToolbarState,
    private val onBackChosen: () -> Unit,
    private val onSendEmailChosen: (String) -> Unit,
    private val onResetPasswordChosen: () -> Unit,
) : ComponentContext by componentContext, EntryEmailComponent, KoinComponent {
    private val sendEmailCodeUseCase: SendEmailCodeUseCase by inject()

    private val scope = coroutineScope(coroutineContext + SupervisorJob())
    override val screenStateFlow = MutableStateFlow(EntryEmailState.EntryEmail(toolbarState))

    private val messageDialogNavigation = SlotNavigation<MessageDialogConfig>()
    override val messageDialog: Value<ChildSlot<*, MessageDialogComponent>> = childSlot(
        source = messageDialogNavigation,
        serializer = MessageDialogConfig.serializer(),
        childFactory = { config, childComponentContext ->
            RealMessageDialogComponent(
                componentContext = childComponentContext,
                state = config,
                onAccessChosen = {
                    when(config){
                        MessageDialogConfig.REGISTRATION_EMAIL_ALREADY_EXISTS -> {
                            onSendEmailClicked(ToolbarState.RESET_PASSWORD)
                            messageDialogNavigation.dismiss()
                        }
                        MessageDialogConfig.RESET_PASSWORD_EMAIL_NOT_REGISTERED -> {
                            onSendEmailClicked(ToolbarState.REGISTRATION)
                            messageDialogNavigation.dismiss()
                        }
                        else ->  messageDialogNavigation.dismiss()
                    }
                },
                onDismissChosen = {
                    onEmailChanged(STRING_EMPTY)
                    messageDialogNavigation.dismiss()
                }
            )
        },
    )

    override fun onEmailChanged(email: String?) =
        screenStateFlow.update { state -> state.copy(email = email.orEmpty()) }

    override fun onSendEmailClicked(state: ToolbarState) {
        onSendEmailChosen(screenStateFlow.value.email)

        scope.launch {
            when(state){
                ToolbarState.REGISTRATION -> {
                    //TODO
                }

                ToolbarState.RESET_PASSWORD -> {
                    //TODO
                }
            }
//            sendEmailCodeUseCase.invoke(screenStateFlow.value.email).onSuccess {
//                onSendEmailChosen(screenStateFlow.value.email)
//            }.onError { code, _ ->
//                handleSendEmailError(code)
//            }
        }
    }

    private fun handleSendEmailError(code: Int) {
        if (screenStateFlow.value.toolbarState == ToolbarState.REGISTRATION) {
            val config = when (code) {
                409 -> MessageDialogConfig.REGISTRATION_EMAIL_ALREADY_EXISTS
                else -> MessageDialogConfig.UNKNOWN_ERROR
            }
            showMessageDialog(config)
        } else {

        }

    }

    override fun showMessageDialog(config: MessageDialogConfig) =
        messageDialogNavigation.activate(config)

    override fun onBackClicked() = onBackChosen()
}