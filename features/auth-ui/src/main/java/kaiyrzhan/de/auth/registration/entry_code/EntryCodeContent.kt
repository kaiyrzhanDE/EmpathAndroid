package kaiyrzhan.de.auth.registration.entry_code

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kaiyrzhan.de.auth.registration.entry_code.components.CodeTextFieldWithKeyboard
import kaiyrzhan.de.core.components.ToolbarText
import kaiyrzhan.de.navigation.auth.model.ToolbarState
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.feature_auth.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EntryCodeContent(
    toolbarState: ToolbarState,
    code: String?,
    onCodeChange: (code: String) -> Unit,
    onRemoveClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onSendCodeClicked: () -> Unit,
) {
    val title = when (toolbarState) {
        ToolbarState.RESET_PASSWORD -> stringResource(id = R.string.reset_password)
        ToolbarState.REGISTRATION -> stringResource(id = R.string.registration)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { ToolbarText(text = title) },
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
            Spacer(modifier = Modifier.weight(1f))
            Description()
            Spacer(modifier = Modifier.height(40.dp))
            CodeTextFieldWithKeyboard(
                code = code.orEmpty(),
                length = 6,
                onNumberClicked = onCodeChange,
                onRemoveClicked = onRemoveClicked,
                onEnterClicked = onSendCodeClicked,
            )
        }
    }
}

@Composable
private fun ColumnScope.Description() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.your_code),
        style = MaterialTheme.typography.headlineMedium,
    )
    Spacer(modifier = Modifier.height(5.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.send_code_description),
        style = MaterialTheme.typography.bodySmall,
    )
}

@Previews
@Composable
private fun Preview() {
    PreviewTheme {
        EntryCodeContent(
            toolbarState = ToolbarState.REGISTRATION,
            code = "",
            onCodeChange = {},
            onSendCodeClicked = {},
            onBackClicked = {},
            onRemoveClicked = {},
        )
    }
}
