package com.postappplus.feature_auth.presentation.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.postappplus.ui.theme.angularBlue
import com.postappplus.ui.theme.angularOrange
import com.postappplus.ui.theme.angularViolet

@Composable
fun TelegramLogoPlus(

) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(angularBlue, angularViolet, angularOrange, angularViolet),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Canvas(modifier = Modifier.size(98.dp)) {
        // Рисуем круг с градиентом
        drawCircle(brush = gradientBrush, radius = size.minDimension / 2)

        val crossSize = size.minDimension / 4
        val crossOffset = (size - Size(crossSize, crossSize)) / 2F

        drawLine(
            color = Color.White,
            start = Offset(crossOffset.width, crossOffset.height + crossSize / 2),
            end = Offset(crossOffset.width + crossSize, crossOffset.height + crossSize / 2),
            strokeWidth = crossSize / 8
        )

        drawLine(
            color = Color.White,
            start = Offset(crossOffset.width + crossSize / 2, crossOffset.height),
            end = Offset(crossOffset.width + crossSize / 2, crossOffset.height + crossSize),
            strokeWidth = crossSize / 8
        )
    }
}
operator fun Size.minus(other: Size): Size {
    return Size(width - other.width, height - other.height)
}