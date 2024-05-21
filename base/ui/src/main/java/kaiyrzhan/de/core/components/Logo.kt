package kaiyrzhan.de.core.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kaiyrzhan.de.core.theme.Gradient
import kaiyrzhan.de.core.theme.fontFamilies
import kaiyrzhan.de.core_ui.R

/**
 * Composable function with app logo and authors.
 */
@Composable
fun ColumnScope.Logo(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        fontSize = 50.sp,
        fontFamily = fontFamilies,
        style = TextStyle(brush = Brush.horizontalGradient(Gradient)),
        fontWeight = FontWeight.Bold,
        text = stringResource(id = R.string.app_name),
    )
    Spacer(modifier = Modifier.height(5.dp))
    Text(
        modifier = modifier,
        fontFamily = fontFamilies,
        style = TextStyle(brush = Brush.horizontalGradient(Gradient)),
        fontWeight = FontWeight.Normal,
        text = stringResource(id = R.string.authors),
    )

}