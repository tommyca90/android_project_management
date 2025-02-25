package com.example.tcpm.composables.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedTextButton(modifier: Modifier, text: String, textColor: Color, containerColor: Color, onClick: () -> Unit) {
    TextButton(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.textButtonColors(containerColor = containerColor),
        onClick = onClick
    ) {
        Text(text, color = textColor)
    }
}