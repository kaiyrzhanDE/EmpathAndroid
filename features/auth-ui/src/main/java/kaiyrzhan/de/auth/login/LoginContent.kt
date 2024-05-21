package kaiyrzhan.de.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kaiyrzhan.de.auth.components.CustomTextField
import kaiyrzhan.de.core.theme.*
import kaiyrzhan.de.core.components.Logo
import kaiyrzhan.de.core.extensions.modifiers.noRippleClickable
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.feature_auth.R
import kaiyrzhan.de.utils.isNotBlankAndNull


@Composable
internal fun LoginContent(
    username: String,
    onUsernameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onRegistrationClicked: () -> Unit,
    onResetPasswordClicked: () -> Unit,
    onGoogleAuthClicked: () -> Unit,
    onFacebookAuthClicked: () -> Unit,
    onPrivacyClicked: () -> Unit,
    onLoginClicked: () -> Unit,
) {
    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Logo(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.weight(1f))
            Login(
                value = username,
                onValueChange = { newUsername -> onUsernameChange(newUsername.orEmpty()) },
                placeholder = stringResource(id = R.string.enter_login),
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = stringResource(id = R.string.email)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Password(
                value = password,
                onValueChange = { newPassword -> onPasswordChange(newPassword.orEmpty()) },
                placeholder = stringResource(
                    id = R.string.enter_password
                ),
                painter = painterResource(id = R.drawable.ic_key),
                contentDescription = stringResource(id = R.string.email),
                onResetPasswordClicked = onResetPasswordClicked,
            )
            Spacer(modifier = Modifier.weight(1f))
            UserActions(
                loginEnabled = username.isNotBlankAndNull() && password.isNotBlankAndNull(),
                onLoginClicked = onLoginClicked,
                onPrivacyClicked = onPrivacyClicked,
                onRegistrationClicked = onRegistrationClicked,
            )
            Spacer(modifier = Modifier.weight(1f))
            AlternativeAuth(
                onGoogleAuthClicked = onGoogleAuthClicked,
                onFacebookAuthClicked = onFacebookAuthClicked,
            )
        }
    }
}

@Composable
private fun ColumnScope.Login(
    value: String,
    onValueChange: (String?) -> Unit,
    placeholder: String,
    painter: Painter,
    contentDescription: String,
) {
    Text(
        modifier = Modifier.align(Alignment.Start),
        text = stringResource(id = R.string.login),
        style = MaterialTheme.typography.bodySmall,
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
            Icon(
                painter = painter,
                contentDescription = contentDescription,
            )
        }
    )
}

@Composable
private fun ColumnScope.Password(
    value: String?,
    onValueChange: (String?) -> Unit,
    placeholder: String,
    painter: Painter,
    contentDescription: String,
    onResetPasswordClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(id = R.string.password),
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            modifier = Modifier.noRippleClickable(onClick = onResetPasswordClicked),
            text = stringResource(id = R.string.reset_password),
            style = MaterialTheme.typography.bodySmall,
            color = Peach,
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    CustomTextField(
        value = value.orEmpty(),
        onValueChange = { newValue -> onValueChange(newValue) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodySmall
            )
        },
        trailingIcon = {
            Icon(
                painter = painter,
                contentDescription = contentDescription,
            )
        }
    )
}

@Composable
private fun ColumnScope.UserActions(
    loginEnabled: Boolean,
    onRegistrationClicked: () -> Unit,
    onPrivacyClicked: () -> Unit,
    onLoginClicked: () -> Unit,
) {
    Text(
        modifier = Modifier.noRippleClickable(onClick = onRegistrationClicked),
        text = stringResource(id = R.string.create_account),
        style = MaterialTheme.typography.titleSmall,
        color = Peach,
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        modifier = Modifier.noRippleClickable(onClick = onPrivacyClicked),
        text = stringResource(id = R.string.privacy),
        style = MaterialTheme.typography.titleSmall,
        color = Peach,
    )
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        onClick = onLoginClicked,
        shape = RoundedCornerShape(10.dp),
        enabled = loginEnabled,
    ) {
        Text(
            text = stringResource(id = R.string.login).uppercase(),
            style = MaterialTheme.typography.titleSmall,
        )
    }
}

@Composable
private fun ColumnScope.AlternativeAuth(
    onGoogleAuthClicked: () -> Unit,
    onFacebookAuthClicked: () -> Unit,
) {
    Text(
        text = stringResource(R.string.authorization_with_),
        style = MaterialTheme.typography.bodyMedium
    )
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = onFacebookAuthClicked,
            shape = RoundedCornerShape(10.dp),
        ) {
            Icon(
                modifier = Modifier.padding(horizontal = 30.dp),
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = stringResource(id = R.string.facebook),
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(
            onClick = onGoogleAuthClicked,
            shape = RoundedCornerShape(10.dp),
        ) {
            Icon(
                modifier = Modifier.padding(horizontal = 30.dp),
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = stringResource(id = R.string.google),
            )
        }
    }
}

@Previews
@Composable
private fun Preview() {
    PreviewTheme {
        LoginContent(
            onGoogleAuthClicked = {},
            onFacebookAuthClicked = {},
            onRegistrationClicked = {},
            onPrivacyClicked = {},
            username = "",
            onUsernameChange = {},
            password = "",
            onPasswordChange = {},
            onResetPasswordClicked = {},
            onLoginClicked = {},
        )
    }
}