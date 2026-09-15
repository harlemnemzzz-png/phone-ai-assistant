package com.phoneai.assistant.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColors(
    primary = Color(0xFF667eea),
    secondary = Color(0xFF764ba2),
    background = Color(0xFF1a1a1a)
)

private val LightColorScheme = lightColors(
    primary = Color(0xFF667eea),
    secondary = Color(0xFF764ba2),
    background = Color(0xFFfafafa)
)

@Composable
fun PhoneAIAssistantTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorScheme,
        typography = androidx.compose.material.Typography(),
        shapes = androidx.compose.material.Shapes(),
        content = content
    )
}
