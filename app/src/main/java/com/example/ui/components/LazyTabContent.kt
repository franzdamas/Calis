package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LazyTabContent(
    key: Any,
    content: @Composable () -> Unit
) {
    var isLoaded by remember(key) { mutableStateOf(false) }

    LaunchedEffect(key) {
        // A very short delay (e.g., 200ms) allows the main tab layout, 
        // navigation bars, and top scaffold to render instantly on the screen first.
        delay(200)
        isLoaded = true
    }

    if (!isLoaded) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(38.dp),
                strokeWidth = 3.dp
            )
        }
    } else {
        AnimatedVisibility(
            visible = true,
            enter = fadeIn()
        ) {
            content()
        }
    }
}
