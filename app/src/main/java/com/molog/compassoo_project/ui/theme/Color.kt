package com.molog.compassoo_project.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val TextColor1 = Color(0xFF4F4F4F)
val TextColor2 = Color(0xFF959595)
val TextColor3 = Color(0xFFD1D1D1)
val TextColor4 = Color(0xFFEEEEEE)

val colorStops = arrayOf(
    0.0f to Color(0x00FFFFFF),
    1.0f to Color(0x9A4F4F4F),
)
val gradientBrush = Brush.verticalGradient(
    colorStops = colorStops
)

val colorStops2 = arrayOf(
    0.0f to Color(0x00FFFFFF),
    1.0f to Color(0xED2C2C2C),
)
val gradientBrush2 = Brush.verticalGradient(
    colorStops = colorStops
)