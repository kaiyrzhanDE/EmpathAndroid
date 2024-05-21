package kaiyrzhan.de.auth.registration.create_account

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kaiyrzhan.de.auth.components.CustomTextField
import kaiyrzhan.de.auth.components.IconWithDescription
import kaiyrzhan.de.core.components.ToolbarText
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.feature_auth.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateAccountContent(
    nickname: String,
    onNicknameChange: (String) -> Unit,
    password: String,
    isPasswordVisible: Boolean,
    onPasswordChange: (String) -> Unit,
    onPasswordShowClicked: () -> Unit,
    repeatedPassword: String,
    isRepeatedPasswordVisible: Boolean,
    onRepeatedPasswordChange: (String) -> Unit,
    onRepeatedPasswordShowClicked: () -> Unit,
    onCreateAccountClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { ToolbarText(text = stringResource(id = R.string.registration)) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
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
                painter = painterResource(id = R.drawable.ic_id_card),
                contentDescription = stringResource(id = R.string.required_information),
                description = stringResource(id = R.string.create_account_description),
            )
            Spacer(modifier = Modifier.height(20.dp))
            TitledTextField(
                title = stringResource(id = R.string.nickname),
                value = nickname,
                onValueChange = { newNickname -> onNicknameChange(newNickname.orEmpty()) },
                placeholder = stringResource(id = R.string.enter_nickname),
                painter = painterResource(id = R.drawable.ic_badge),
                contentDescription = stringResource(id = R.string.nickname),
            )
            Spacer(modifier = Modifier.height(20.dp))
            TitledTextField(
                title = stringResource(id = R.string.password),
                onIconClicked = onPasswordShowClicked,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                value = password,
                onValueChange = { newPassword -> onPasswordChange(newPassword.orEmpty()) },
                placeholder = stringResource(id = R.string.enter_password),
                painter = painterResource(
                    id = if (isPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                ),
                contentDescription = stringResource(id = R.string.show_password),
            )
            Spacer(modifier = Modifier.height(20.dp))
            TitledTextField(
                title = stringResource(id = R.string.repeated_password),
                onIconClicked = onRepeatedPasswordShowClicked,
                visualTransformation = if (isRepeatedPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                value = repeatedPassword,
                onValueChange = { newPassword -> onRepeatedPasswordChange(newPassword.orEmpty()) },
                placeholder = stringResource(id = R.string.enter_password),
                painter = painterResource(
                    id = if (isRepeatedPasswordVisible) R.drawable.ic_visibility_off
                    else R.drawable.ic_visibility
                ),
                contentDescription = stringResource(id = R.string.show_password),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onCreateAccountClicked,
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.create_account).uppercase(),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            Spacer(modifier = Modifier.weight(3f))
        }
    }
}

@Composable
private fun ColumnScope.TitledTextField(
    title: String,
    value: String,
    onValueChange: (String?) -> Unit,
    placeholder: String,
    onIconClicked: () -> Unit,
    painter: Painter,
    contentDescription: String,
    visualTransformation: VisualTransformation,
) {
    Text(
        modifier = Modifier.align(Alignment.Start),
        text = title,
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
            IconButton(onClick = onIconClicked) {
                Icon(painter = painter, contentDescription = contentDescription)
            }
        },
        visualTransformation = visualTransformation,
    )
}

@Composable
private fun ColumnScope.TitledTextField(
    title: String,
    value: String,
    onValueChange: (String?) -> Unit,
    placeholder: String,
    painter: Painter,
    contentDescription: String,
) {
    Text(
        modifier = Modifier.align(Alignment.Start),
        text = title,
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
        CreateAccountContent(
            nickname = "",
            onNicknameChange = {},
            password = "",
            onPasswordChange = {},
            onPasswordShowClicked = {},
            repeatedPassword = "",
            onRepeatedPasswordChange = {},
            onRepeatedPasswordShowClicked = {},
            onCreateAccountClicked = {},
            onBackClicked = {},
            isPasswordVisible = true,
            isRepeatedPasswordVisible = false,
        )
    }
}