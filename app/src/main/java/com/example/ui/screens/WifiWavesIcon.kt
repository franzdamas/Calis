package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun WifiWavesIcon(
    color: Color,
    modifier: Modifier = Modifier
) {
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(durationMillis = 800),
        label = "WifiWavesColor"
    )
    Canvas(modifier = modifier.size(24.dp)) {
        val strokeWidth = 2.5.dp.toPx()
        
        // Center of the circles is on the right edge, middle vertically
        val cx = size.width
        val cy = size.height / 2f
        
        // Radii for the 3 arcs
        val rSmall = size.width * 0.25f
        val rMedium = size.width * 0.6f
        val rLarge = size.width * 0.95f
        
        // 135 degrees start + 90 degrees sweep creates a 'C' shape facing right
        // Centered around 180 degrees (9 o'clock)
        val startAngle = 135f
        val sweepAngle = 90f
        
        // Small C (rightmost, closest to center)
        drawArc(
            color = animatedColor,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(cx - rSmall, cy - rSmall),
            size = Size(rSmall * 2, rSmall * 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
        
        // Medium C
        drawArc(
            color = animatedColor,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(cx - rMedium, cy - rMedium),
            size = Size(rMedium * 2, rMedium * 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
        
        // Large C (leftmost)
        drawArc(
            color = animatedColor,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(cx - rLarge, cy - rLarge),
            size = Size(rLarge * 2, rLarge * 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}
