package kaiyrzhan.de.core.extensions.modifiers

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.paddingAnimation(
    isVisible: Boolean,
    from: Dp,
    to: Dp
) = composed {
    val padding = animateDpAsState(if (isVisible) to else from, label = "PaddingAnimation")
    then(this.padding(padding.value))
}
