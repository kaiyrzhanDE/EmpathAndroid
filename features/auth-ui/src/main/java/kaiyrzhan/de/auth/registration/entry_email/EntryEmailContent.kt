package kaiyrzhan.de.auth.registration.entry_email

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import kaiyrzhan.de.auth.components.CustomTextField
import kaiyrzhan.de.auth.components.IconWithDescription
import kaiyrzhan.de.auth.dialog.MessageDialogContent
import kaiyrzhan.de.auth.registration.entry_email.model.EntryEmailState
import kaiyrzhan.de.core.components.ToolbarText
import kaiyrzhan.de.auth.model.ToolbarState
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.feature_auth.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EntryEmailContent(
    component: EntryEmailComponent,
) {
    val screenState = component.screenStateFlow.collectAsState()

    val messageDialogSlot = component.messageDialog.subscribeAsState()

    messageDialogSlot.value.child?.instance?.also { childComponent ->
        MessageDialogContent(component = childComponent)
    }

    when (val state = screenState.value) {
        is EntryEmailState.None -> {}
        is EntryEmailState.Loading -> {
            //TODO Loading Animation
        }

        is EntryEmailState.EntryEmail -> {
            val title = when (state.toolbarState) {
                ToolbarState.RESET_PASSWORD -> stringResource(id = R.string.reset_password)
                ToolbarState.REGISTRATION -> stringResource(id = R.string.registration)
            }

            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { ToolbarText(text = title) },
                        navigationIcon = {
                            IconButton(onClick = component::onBackClicked) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_arrow_backward),
                                    contentDescription = stringResource(id = R.string.back),
                                    tint = Color.Unspecified,
                                )
                            }
                        }
                    )
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 20.dp, vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.weight(0.5f))
                    IconWithDescription(
                        painter = painterResource(id = R.drawable.ic_send_mail),
                        contentDescription = stringResource(id = R.string.email),
                        description = stringResource(id = R.string.send_email_description),
                    )
                    Spacer(modifier = Modifier.weight(0.5f))
                    Email(
                        value = state.email,
                        onValueChange = component::onEmailChanged,
                        placeholder = stringResource(id = R.string.enter_email),
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = stringResource(id = R.string.email)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { component.onSendEmailClicked(state.toolbarState) },
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.send_code).uppercase(),
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                    Spacer(modifier = Modifier.weight(3f))
                }

            }

        }
    }
}

@Composable
private fun ColumnScope.Email(
    value: String,
    onValueChange: (String?) -> Unit,
    placeholder: String,
    painter: Painter,
    contentDescription: String,
) {
    Text(
        modifier = Modifier.align(Alignment.Start),
        text = stringResource(id = R.string.email),
        style = MaterialTheme.typography.bodyMedium,
    )
    Spacer(modifier = Modifier.height(8.dp))
    CustomTextField(
        value = value,
        onValueChange = { newValue -> onValueChange(newValue) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodySmall
            )
        },
        trailingIcon = {
            Icon(painter = painter, contentDescription = contentDescription)
        }
    )
}

@Previews
@Composable
private fun Preview() {
    PreviewTheme {
        EntryEmailContent(FakeEntryEmailComponent())
    }
}

