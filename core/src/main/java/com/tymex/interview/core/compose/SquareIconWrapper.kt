package com.tymex.interview.core.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun SquareIconWrapper(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(0.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer, // Default background
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(
                color = backgroundColor,
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
