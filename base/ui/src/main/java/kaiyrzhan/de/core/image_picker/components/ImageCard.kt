package kaiyrzhan.de.core.image_picker.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kaiyrzhan.de.core.image_picker.model.Image
import kaiyrzhan.de.core.extensions.modifiers.borderAnimation
import kaiyrzhan.de.core.extensions.modifiers.paddingAnimation
import kaiyrzhan.de.core.theme.Peach
import kaiyrzhan.de.core.theme.White
import kaiyrzhan.de.core_ui.R

private val icons = lazy {
    mapOf(
        Pair(1, R.drawable.ic_counter_1),
        Pair(2, R.drawable.ic_counter_2),
        Pair(3, R.drawable.ic_counter_3),
        Pair(4, R.drawable.ic_counter_4),
        Pair(5, R.drawable.ic_counter_5),
        Pair(6, R.drawable.ic_counter_6),
        Pair(7, R.drawable.ic_counter_7),
        Pair(8, R.drawable.ic_counter_8),
        Pair(9, R.drawable.ic_counter_9),
    )
}

@Composable
internal fun ImageCard(
    index: Int,
    image: Image,
    isMultiplePicker: Boolean,
    onImageSelected: () -> Unit,
) {
    Box(
        modifier = Modifier
            .animateContentSize()
            .aspectRatio(1f)
            .paddingAnimation(isVisible = image.isSelected, to = 5.dp, from = 0.dp)
            .borderAnimation(
                isVisible = image.isSelected,
                width = 1.dp,
                color = Peach,
                shape = RoundedCornerShape(
                    topStart = if (index == 0) 10.dp else 0.dp,
                    topEnd = if (index == 2) 10.dp else 0.dp
                )
            )
            .clickable(onClick = onImageSelected)

    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    shape = RoundedCornerShape(
                        topStart = if (index == 0) 10.dp else 0.dp,
                        topEnd = if (index == 2) 10.dp else 0.dp,
                    )
                ),
            model = image.uri,
            contentScale = ContentScale.Crop,
            contentDescription = image.name,
        )
        Icon(
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.TopEnd)
                .padding(5.dp),
            tint = if (image.isSelected) Peach else White,
            painter = painterResource(
                id = if (image.isSelected) getSelectedIcon(
                    isMultiplePicker = isMultiplePicker,
                    imagePosition = image.selectedPosition,
                ) else R.drawable.ic_not_selected_circle
            ),
            contentDescription =  if (image.isSelected) "Selected" else "Not selected"
        )
    }
}

private fun getSelectedIcon(isMultiplePicker: Boolean, imagePosition: Int?): Int {
    return if (isMultiplePicker) {
        imagePosition?.let { position -> icons.value[position + 1] }
            ?: R.drawable.ic_selected_circle
    } else R.drawable.ic_selected_circle
}
