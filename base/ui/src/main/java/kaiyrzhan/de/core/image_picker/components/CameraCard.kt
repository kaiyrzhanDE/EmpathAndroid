package kaiyrzhan.de.core.image_picker.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kaiyrzhan.de.core.theme.Peach
import kaiyrzhan.de.core_ui.R

@Composable
internal fun CameraCard(
    onCameraClicked: () -> Unit,
) {
    Card(
        modifier = Modifier.aspectRatio(1f),
        border = BorderStroke(width = 1.dp, color = Peach),
        shape = RoundedCornerShape(topStart = 10.dp),
        onClick = onCameraClicked,
        colors = CardDefaults.cardColors(Color.Unspecified)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f)
                .padding(30.dp),
            painter = painterResource(id = R.drawable.ic_add_a_photo),
            contentDescription = "Camera",
            tint = Peach,
        )
    }
}