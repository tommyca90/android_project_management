package com.example.tcpm.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.tcpm.R

@Composable
fun BoxWithWaitIndicatorOverlay(
    modifier: Modifier,
    showWaitIndicator: Boolean = false,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        content()
        if (showWaitIndicator) {
            Column(
                modifier = Modifier
                    .matchParentSize()
                    .background(color = colorResource(R.color.white_transparent))
                    .pointerInput(PointerEventPass.Initial) {
                        awaitEachGesture {
                            val down = awaitFirstDown(pass = PointerEventPass.Initial)
                            down.consume()
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(128.dp)
                        .height(128.dp),
                    color = colorResource(R.color.theme_green),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    }
}