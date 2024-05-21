package kaiyrzhan.de.core.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kaiyrzhan.de.core.theme.Gradient

@Composable
fun ToolbarText(text: String){
    Text(
        text = text,
        style = TextStyle(brush = Brush.linearGradient(Gradient)),
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
    )
}