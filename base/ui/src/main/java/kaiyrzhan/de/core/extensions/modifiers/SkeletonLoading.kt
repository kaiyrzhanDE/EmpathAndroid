package kaiyrzhan.de.core.extensions.modifiers


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import kaiyrzhan.de.core_ui.R


/**
 * Modifier extension function to render a skeleton loading indicator.
 *
 * @param widthOfShadowBrush Width of brush line.
 * @param angleOfAxisY Angle of axis Y, along which the animation will take place.
 * @param durationMillis Duration of a single cycle of the loader animation in milliseconds.
 *
 * When you decrease widthOfShadowBrush, you need to update angleOfAxisY because drawing bold and thin lines
 * are not the same, do not forget.
 */
fun Modifier.skeletonLoading(
    isLoading: Boolean = false,
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 290f,
    durationMillis: Int = 1000,
): Modifier {
    return composed {
        if (isLoading) {
            val color = MaterialTheme.colorScheme.onBackground
            val shimmerColors = listOf(
                color.copy(alpha = 0.3f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 1.0f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 0.3f),
            )

            val transition =
                rememberInfiniteTransition(label = stringResource(id = R.string.loading))

            val translateAnimation = transition.animateFloat(
                initialValue = 0f,
                targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = durationMillis,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart,
                ),
                label = stringResource(id = R.string.loading),
            )

            this.background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                    end = Offset(x = translateAnimation.value, y = angleOfAxisY),
                ),
            )

        } else {
            Modifier
        }
    }
}
