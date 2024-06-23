package kaiyrzhan.de.auth.registration.reset_password

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kaiyrzhan.de.auth.components.CustomTextField
import kaiyrzhan.de.auth.components.IconWithDescription
import kaiyrzhan.de.auth.registration.reset_password.model.ResetPasswordState
import kaiyrzhan.de.core.components.ToolbarText
import kaiyrzhan.de.core.navigation.BackHandler
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.feature_auth.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ResetPasswordContent(component: ResetPasswordComponent) {

    BackHandler(component.backHandler) {
        component.onBackClicked()
    }

    val screenState = component.screenStateFlow.collectAsState()

    when (val state = screenState.value) {
        is ResetPasswordState.None -> {}
        is ResetPasswordState.Loading -> {
            //TODO Loading Animation
        }

        is ResetPasswordState.ResetPassword -> {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { ToolbarText(text = stringResource(id = R.string.reset_password)) },
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
                        painter = painterResource(id = R.drawable.ic_lock_reset),
                        contentDescription = stringResource(id = R.string.reset_password),
                        description = stringResource(id = R.string.reset_password_description),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TitledTextField(
                        title = stringResource(id = R.string.password),
                        onIconClicked = component::onPasswordShowClicked,
                        value = state.password,
                        onValueChange = component::onPasswordChanged,
                        placeholder = stringResource(id = R.string.enter_password),
                        painter = painterResource(id = if (state.isPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility),
                        contentDescription = stringResource(id = R.string.show_password),
                        visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TitledTextField(
                        title = stringResource(id = R.string.repeated_password),
                        value = state.repeatedPassword,
                        onValueChange = component::onRepeatedPasswordChanged,
                        placeholder = stringResource(id = R.string.enter_password),
                        painter = painterResource(id = if (state.isRepeatedPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility),
                        contentDescription = stringResource(id = R.string.show_password),
                        onIconClicked = component::onRepeatedPasswordShowClicked,
                        visualTransformation = if (state.isRepeatedPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = component::onResetPasswordClicked,
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.reset_password).uppercase(),
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

@Previews
@Composable
private fun Preview() = PreviewTheme {
    ResetPasswordContent(component = FakeResetPasswordComponent())
}
