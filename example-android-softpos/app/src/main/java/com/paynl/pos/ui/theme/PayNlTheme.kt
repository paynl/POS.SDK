package com.paynl.pos.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paynl.pos.R

@OptIn(ExperimentalTextApi::class)
val fontInterRegular = FontFamily(
    Font(
        R.font.inter_regular,
        variationSettings = FontVariation.Settings(FontVariation.weight(400))
    )
)

@OptIn(ExperimentalTextApi::class)
val fontLufgaSemiBold = FontFamily(
    Font(
        R.font.lufga_semibold,
        variationSettings = FontVariation.Settings(FontVariation.weight(700))
    )
)

private val PayNlLightColorScheme = lightColorScheme(
    primary = Color(0xFF585FFF),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFFDEDFFF),
    onSecondary = Color(0xFFDEDFFF),
    tertiary = Color(0xFFf2f6fc),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1D1D1D),
    outline = Color(0xFFDEDFFF) // TextField border color
)

private val PayNlTypography = Typography(
    titleLarge = TextStyle(fontFamily = fontLufgaSemiBold, fontSize = 20.sp, color = Color(0xFF1D1D1D)),
    titleMedium = TextStyle(fontFamily = fontLufgaSemiBold, fontSize = 18.sp, color = Color(0xFF1D1D1D)),
    titleSmall = TextStyle(fontFamily = fontLufgaSemiBold, fontSize = 16.sp, color = Color(0xFF1D1D1D)),
    bodyMedium = TextStyle(fontFamily = fontInterRegular, fontSize = 16.sp, color = Color(0xFF1D1D1D)),
    bodySmall = TextStyle(fontFamily = fontInterRegular, fontSize = 14.sp, color = Color(0xFF1D1D1D)),
    labelMedium = TextStyle(fontFamily = fontInterRegular, fontSize = 16.sp, color = Color(0x801D1D1D)), // 50% opacity
    labelSmall = TextStyle(fontFamily = fontInterRegular, fontSize = 14.sp, color = Color(0x801D1D1D)) // 50% opacity
)

private val PayNlShapes = Shapes(
    small = RoundedCornerShape(12.dp),
)

@Composable
fun PayNlTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PayNlLightColorScheme,
        typography = PayNlTypography,
        shapes = PayNlShapes,
        content = content
    )
}
