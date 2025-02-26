package com.example.tcpm.composables.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.tcpm.R

@Composable
fun RoundedOutlineTextButton(
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color = Color.White,
    contrastColor: Color = colorResource(
        R.color.theme_green
    ),
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        border = BorderStroke(1.dp, contrastColor),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = containerColor),
        onClick = onClick
    ) {
        Text(text, color = contrastColor, style = MaterialTheme.typography.labelLarge)
    }
}