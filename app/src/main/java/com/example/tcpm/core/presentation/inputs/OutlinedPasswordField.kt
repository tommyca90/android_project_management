package com.example.tcpm.core.presentation.inputs

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.tcpm.core.presentation.icons.VisibilityIcon

@Composable
fun OutlinedPasswordField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String)->Unit,
    label: String,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
){
    var isPwdVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = if (isPwdVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            VisibilityIcon(
                isPwdVisible,
                onChangeVisibility = { isVisible -> isPwdVisible = isVisible })
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = colors
    )
}