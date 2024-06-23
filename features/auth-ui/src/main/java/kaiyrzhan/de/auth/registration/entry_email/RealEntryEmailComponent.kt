package kaiyrzhan.de.auth.registration.entry_email

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogComponent
import kaiyrzhan.de.auth.dialog.message_dialog.RealMessageDialogComponent
import kaiyrzhan.de.auth.dialog.message_dialog.MessageDialogConfig
import kaiyrzhan.de.auth.domain.usecase.SendEmailCodeUseCase
import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailState
import kaiyrzhan.de.auth.model.ToolbarState
import kaiyrzhan.de.core.network.onError
import kaiyrzhan.de.core.network.onSuccess
import kaiyrzhan.de.utils.dispatcher.AppDispatchers
import kaiyrzhan.de.utils.kotlin.STRING_EMPTY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class RealEntryEmailComponent(
    componentContext: ComponentContext,
    toolbarState: ToolbarState,
    private val onBackChosen: () -> Unit,
    private val onSendEmailChosen: (String) -> Unit,
    private val onResetPasswordChosen: () -> Unit,
) : ComponentContext by componentContext, EntryEmailComponent, KoinComponent {
    private val dispatchers: AppDispatchers by inject()
    private val sendEmailCodeUseCase: SendEmailCodeUseCase by inject()

    private val scope = coroutineScope(dispatchers.main + SupervisorJob())
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
                    when (config) {
                        MessageDialogConfig.REGISTRATION_EMAIL_ALREADY_EXISTS -> {
                            onSendEmailClicked(ToolbarState.RESET_PASSWORD)
                            messageDialogNavigation.dismiss()
                        }

                        MessageDialogConfig.RESET_PASSWORD_EMAIL_NOT_REGISTERED -> {
                            onSendEmailClicked(ToolbarState.REGISTRATION)
                            messageDialogNavigation.dismiss()
                        }

                        else -> messageDialogNavigation.dismiss()
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
        //TODO(Retrofit can't create adapter for object maybe library error)
//        scope.launch {
//            when (state) {
//                ToolbarState.REGISTRATION -> {
//                    sendEmailCodeUseCase.invoke(screenStateFlow.value.email)
//                        .onSuccess {
//                            onSendEmailChosen(screenStateFlow.value.email)
//                        }.onError { code, _ ->
//                            handleSendEmailError(state, code)
//                        }
//                }
//
//                ToolbarState.RESET_PASSWORD -> {
//                    //TODO(another request for sending code for reset password)
//                    onResetPasswordChosen()
//                }
//            }
//        }
    }

    private fun handleSendEmailError(state: ToolbarState, code: Int) {
        val config = when (state) {
            ToolbarState.REGISTRATION -> {
                when (code) {
                    409 -> MessageDialogConfig.REGISTRATION_EMAIL_ALREADY_EXISTS
                    422 -> MessageDialogConfig.TOO_MANY_VERIFICATION_ATTEMPTS
                    else -> MessageDialogConfig.UNKNOWN_ERROR
                }
            }

            ToolbarState.RESET_PASSWORD -> {
                when (code) {
                    409 -> MessageDialogConfig.RESET_PASSWORD_EMAIL_NOT_REGISTERED
                    422 -> MessageDialogConfig.TOO_MANY_VERIFICATION_ATTEMPTS
                    else -> MessageDialogConfig.UNKNOWN_ERROR
                }
            }
        }
        showMessageDialog(config)
    }

    override fun showMessageDialog(config: MessageDialogConfig) =
        messageDialogNavigation.activate(config)

    override fun onBackClicked() = onBackChosen()
}