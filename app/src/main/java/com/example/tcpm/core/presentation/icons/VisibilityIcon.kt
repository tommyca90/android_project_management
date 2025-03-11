package com.example.tcpm.core.presentation.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.res.stringResource
import com.example.tcpm.R

@Composable
fun VisibilityIcon(isVisible: Boolean, onChangeVisibility: (Boolean) -> Unit) {
    val image = if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    val description = if (isVisible) stringResource(R.string.app_pwd_hide) else stringResource(R.string.app_pwd_show)

    IconButton(onClick = { onChangeVisibility(!isVisible) }) {
        Icon(imageVector = image, description)
    }
}