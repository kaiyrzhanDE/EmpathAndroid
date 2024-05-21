package kaiyrzhan.de.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kaiyrzhan.de.core_ui.R

val fontFamilies = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val textStyles = Typography(
    labelSmall = TextStyle(
        fontSize = 8.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.5).sp
    ),
    labelMedium = TextStyle(
        fontSize = 11.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.5).sp
    ),
    labelLarge = TextStyle(
        fontSize = 13.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.5).sp
    ),
    bodySmall = TextStyle(
        fontSize = 15.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.5).sp
    ),
    bodyMedium = TextStyle(
        fontSize = 17.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.5).sp
    ),
    bodyLarge = TextStyle(
        fontSize = 19.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.5).sp
    ),
    titleSmall = TextStyle(
        fontSize = 15.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Medium,
        letterSpacing = (0.5).sp
    ),
    titleMedium = TextStyle(
        fontSize = 17.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Medium,
        letterSpacing = (0.5).sp
    ),
    titleLarge = TextStyle(
        fontSize = 19.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Medium,
        letterSpacing = (0.5).sp
    ),
    headlineSmall = TextStyle(
        fontSize = 19.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Bold,
        letterSpacing = (0.5).sp
    ),
    headlineMedium = TextStyle(
        fontSize = 21.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Bold,
        letterSpacing = (0.5).sp
    ),
    headlineLarge = TextStyle(
        fontSize = 23.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Bold,
        letterSpacing = (0.5).sp
    ),
    displaySmall = TextStyle(
        fontSize = 25.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Bold,
        letterSpacing = (0.5).sp
    ),
    displayMedium = TextStyle(
        fontSize = 27.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Bold,
        letterSpacing = (0.5).sp
    ),
    displayLarge = TextStyle(
        fontSize = 29.sp,
        fontFamily = fontFamilies,
        fontWeight = FontWeight.Bold,
        letterSpacing = (0.5).sp
    ),
)


