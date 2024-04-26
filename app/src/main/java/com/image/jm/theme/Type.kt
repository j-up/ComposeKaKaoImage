package com.image.jm.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.image.jm.R
import javax.annotation.concurrent.Immutable

@Immutable
data class ImageTypography(
    private val colors: ImageColors,
    val baseTextStyle: TextStyle = TextStyle(
        color = colors.primary,
        fontFamily = ImageGrotesk
    ),
    val h1: TextStyle = baseTextStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp,
        letterSpacing = 0.sp
    ),
    val h2: TextStyle = baseTextStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        letterSpacing = 0.5.sp
    ),
    val input: TextStyle = baseTextStyle.copy(
        fontWeight = FontWeight.SemiBold,
        color = colors.textColorPrimary,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp
    ),
    val bold: TextStyle = baseTextStyle.copy(
        color = colors.textColorPrimary,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        letterSpacing = 0.5.sp
    )
)

private val ImageGrotesk = FontFamily(
    Font(R.font.nanum_gothic_extra_bold, FontWeight.W700),
    Font(R.font.nanum_gothic_bold, FontWeight.W600),
    Font(R.font.nanum_gothic_light, FontWeight.W200),
    Font(R.font.nanum_gothic, FontWeight.W500),
)
