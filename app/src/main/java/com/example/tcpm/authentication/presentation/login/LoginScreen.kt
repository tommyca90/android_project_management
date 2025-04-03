package com.example.tcpm.authentication.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.R
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.authentication.presentation.register.Error
import com.example.tcpm.core.presentation.BoxWithWaitIndicatorOverlay
import com.example.tcpm.core.presentation.NavigationIconType
import com.example.tcpm.core.presentation.ScreenUnauthenticated

@Composable
fun LoginScreen(
    navManager: NavManager,
    authViewModel: AuthenticationViewModel,
    loginViewModel: LoginViewModel = viewModel()
) {
    val email by loginViewModel.email
    val password by loginViewModel.password
    val isLogInInProgress by authViewModel.isLogInInProgress
    val errorEmail by authViewModel.errorEmail
    val errorPassword by authViewModel.errorPassword
    val errorLogIn by authViewModel.errorLogIn

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        authViewModel.resetErrors()
    }

    ScreenUnauthenticated(
        navManager = navManager,
        title = stringResource(R.string.app_log_in),
        navigationIconType = NavigationIconType.BACK
    ) {
        BoxWithWaitIndicatorOverlay(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            showWaitIndicator = isLogInInProgress
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val modifierOutlinedTextFields = Modifier.fillMaxWidth()
                val configuredColors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.theme_green),
                    focusedLabelColor = colorResource(R.color.theme_green)
                )
                Text(
                    text = stringResource(R.string.app_login_intro)
                )
                OutlinedTextField(
                    modifier = modifierOutlinedTextFields,
                    value = loginViewModel.email.value,
                    onValueChange = { loginViewModel.onChangeEmail(it) },
                    label = { Text(stringResource(R.string.title_email)) },
                    colors = configuredColors,
                    supportingText = { Text(errorEmail.ifEmpty { "*required" }) },
                    isError = errorEmail.isNotEmpty(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )
                OutlinedTextField(
                    modifier = modifierOutlinedTextFields,
                    value = loginViewModel.password.value,
                    onValueChange = { loginViewModel.onChangePassword(it) },
                    label = { Text(stringResource(R.string.app_pwd)) },
                    colors = configuredColors,
                    supportingText = { Text(errorPassword.ifEmpty { "*required" }) },
                    isError = errorPassword.isNotEmpty(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    visualTransformation = PasswordVisualTransformation()
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            authViewModel.logInUser(
                                email = email,
                                password = password
                            )
                        },
                        colors = ButtonDefaults.buttonColors()
                            .copy(containerColor = colorResource(R.color.theme_green))
                    ) {
                        Text(stringResource(R.string.app_log_in))
                    }
                    Error(errorLogIn)
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navManager.navigateUp() },
                    colors = ButtonDefaults.outlinedButtonColors().copy(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(R.color.theme_green)
                    )
                ) {
                    Text(stringResource(R.string.app_cancel))
                }
            }
        }
    }
}