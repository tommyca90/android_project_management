package com.example.tcpm.core.presentation.buttons

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.tcpm.R

@Composable
fun RoundedTextButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    containerColor: Color = colorResource(
        R.color.theme_green
    ),
    showWaitIndicator: Boolean = false,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.textButtonColors(containerColor = containerColor),
        onClick = if(showWaitIndicator) {{}} else onClick
    ) {
        if(showWaitIndicator){
            CircularProgressIndicator(
                modifier = Modifier.fillMaxHeight(),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }else {
            Text(text, color = textColor)
        }
    }
}