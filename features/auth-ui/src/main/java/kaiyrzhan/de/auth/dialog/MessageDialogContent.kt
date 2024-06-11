package kaiyrzhan.de.auth.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.core.theme.SlateGray
import kaiyrzhan.de.feature_auth.R

@Composable
internal fun MessageDialogContent(component: MessageDialogComponent) {
    val state = component.screenStateFlow.collectAsState()
    val (title, description) = getDialogContent(state = state.value)
    val (dismiss, access) = getDialogActions(state = state.value)

    Dialog(onDismissRequest = component::onDismissClicked) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp),
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = description,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(IntrinsicSize.Max),
                    onClick = component::onDismissClicked,
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = dismiss,
                        textAlign = TextAlign.Center,
                        maxLines = 3,
                        style = MaterialTheme.typography.titleSmall,
                        color = SlateGray,
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                TextButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(IntrinsicSize.Max),
                    onClick = component::onAccessClicked,
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        text = access,
                        maxLines = 3,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
    }
}

@Composable
private fun getDialogContent(state: MessageDialogConfig) = when (state) {
    MessageDialogConfig.REGISTRATION_EMAIL_ALREADY_EXISTS ->
        getStringResourcePair(R.string.email_registered, R.string.email_already_exists)

    MessageDialogConfig.RESET_PASSWORD_EMAIL_NOT_REGISTERED ->
        getStringResourcePair(R.string.email_not_registered, R.string.email_does_not_exists)

    MessageDialogConfig.ABORT_REGISTRATION ->
        getStringResourcePair(R.string.abort_registration, R.string.abort_registration_warning)

    MessageDialogConfig.ABORT_RESET_PASSWORD ->
        getStringResourcePair(R.string.abort_password_recovery, R.string.abort_password_recovery_warning)

    MessageDialogConfig.UNKNOWN_ERROR ->
        getStringResourcePair(R.string.oops_something_be_wrong, R.string.try_again_later)
}

@Composable
private fun getDialogActions(state: MessageDialogConfig) = when (state) {
    MessageDialogConfig.REGISTRATION_EMAIL_ALREADY_EXISTS ->
        getStringResourcePair(R.string.new_email, R.string.reset_password)

    MessageDialogConfig.RESET_PASSWORD_EMAIL_NOT_REGISTERED ->
        getStringResourcePair(R.string.new_email, R.string.registration)

    MessageDialogConfig.ABORT_REGISTRATION, MessageDialogConfig.ABORT_RESET_PASSWORD ->
        getStringResourcePair(R.string.abort, R.string.resume)

    MessageDialogConfig.UNKNOWN_ERROR ->
        getStringResourcePair(R.string.close, R.string.okay)
}

@Composable
private fun getStringResourcePair(firstId: Int, secondId: Int): Pair<String, String> {
    return stringResource(id = firstId) to stringResource(id = secondId)
}

@Composable
@Previews
private fun Preview() {
    PreviewTheme {
        MessageDialogContent(component = FakeMessageDialogComponent())
    }
}