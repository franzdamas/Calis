package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AnimatedDottedLine(
    isActive: Boolean,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF3B82F6),
    inactiveColor: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
) {
    // We want to animate from right to left (button to esp32)
    val headProgress = remember { Animatable(if (isActive) 1f else 0f) }
    val tailProgress = remember { Animatable(if (isActive) 0f else 1f) }

    LaunchedEffect(isActive) {
        if (isActive) {
            // When becoming active, reset tail to 0 instantly, then animate head to 1
            tailProgress.snapTo(0f)
            headProgress.animateTo(1f, animationSpec = tween(800, easing = LinearEasing))
        } else {
            // When becoming inactive, animate tail to 1, then reset head to 0
            tailProgress.animateTo(1f, animationSpec = tween(800, easing = LinearEasing))
            headProgress.snapTo(0f)
        }
    }

    Canvas(modifier = modifier.fillMaxWidth()) {
        val dotRadius = 3.dp.toPx()
        val spacing = 12.dp.toPx()
        val totalWidth = size.width
        val y = size.height / 2f
        
        val totalDots = maxOf(1, ((totalWidth - dotRadius * 2) / spacing).toInt() + 1)
        val drawnWidth = (totalDots - 1) * spacing
        val startX = (totalWidth - drawnWidth) / 2f
        
        for (i in 0 until totalDots) {
            val x = startX + (i * spacing)
            
            // Normalized position from right to left (rightmost dot is index totalDots - 1)
            val dotPos = (totalDots - 1 - i).toFloat() / totalDots.toFloat()
            val isBlue = dotPos < headProgress.value && dotPos >= tailProgress.value
            
            val color = if (isBlue) activeColor else inactiveColor
            
            drawCircle(
                color = color,
                radius = dotRadius,
                center = Offset(x, y)
            )
        }
    }
}
