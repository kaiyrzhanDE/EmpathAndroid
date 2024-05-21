package kaiyrzhan.de.auth.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.IconWithDescription(
    painter: Painter,
    contentDescription: String? = null,
    description: String,
) {
    Icon(
        modifier = Modifier.size(100.dp),
        painter = painter,
        contentDescription = contentDescription,
    )
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = description,
        style = MaterialTheme.typography.bodySmall,
    )
}