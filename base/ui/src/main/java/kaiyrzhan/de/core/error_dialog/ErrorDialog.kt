package kaiyrzhan.de.core.error_dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kaiyrzhan.de.core_ui.R
import kaiyrzhan.de.utils.isNotNullAndEmpty

@Composable
fun ErrorDialog(
    iconResId: Int,
    titleResId: Int,
    contentDescription: String? = null,
    description: String? = null,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(modifier = Modifier.padding(all = 20.dp)) {
            Icon(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = titleResId),
                style = MaterialTheme.typography.titleMedium,
            )
            if (description.isNotNullAndEmpty()) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = description.orEmpty()
                )
            }

            Button(
                onClick = onDismissRequest,
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 30.dp),
                    text = stringResource(id = R.string.okay).uppercase(),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}