package kaiyrzhan.de.core.extensions.modifiers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale

@Composable
fun Modifier.scaleAnimation(
    isVisible: Boolean,
    from: Float,
    to: Float
) = composed {
    val scale = animateFloatAsState(if (isVisible) to else from, label = "ScaleAnimation")
    then(this.scale(scale.value))
}