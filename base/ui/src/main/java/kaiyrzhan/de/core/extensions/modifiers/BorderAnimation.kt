package kaiyrzhan.de.core.extensions.modifiers

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.borderAnimation(
    isVisible: Boolean,
    width: Dp,
    color: Color,
    shape: RoundedCornerShape,
): Modifier {
    return if (isVisible) this.border(width, color, shape)
    else this
}