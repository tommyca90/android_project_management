package com.example.tcpm.authentication.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.R
import com.example.tcpm.core.presentation.TopAppBarView
import com.example.tcpm.core.presentation.buttons.RoundedOutlineTextButton
import com.example.tcpm.core.presentation.buttons.RoundedTextButton
import com.example.tcpm.core.presentation.inputs.OutlinedPasswordField
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.authentication.presentation.register.Error

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

    Scaffold(
        topBar = {
            TopAppBarView(
                title = stringResource(R.string.app_log_in),
                true,
                onBackNavClicked = { navManager.navigateUp() })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.app_login_intro)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    val modifierOutlinedTextFields = Modifier.fillMaxWidth()
                    val configuredColors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(R.color.theme_green),
                        focusedLabelColor = colorResource(R.color.theme_green)
                    )
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            modifier = modifierOutlinedTextFields,
                            value = email,
                            onValueChange = { loginViewModel.onEmailChanged(it) },
                            label = { Text(stringResource(R.string.title_email)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = configuredColors
                        )
                        Error(errorEmail)
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedPasswordField(
                            modifier = modifierOutlinedTextFields,
                            value = password,
                            onValueChange = { loginViewModel.onPasswordChanged(it) },
                            label = stringResource(R.string.app_pwd),
                            colors = configuredColors
                        )
                        Error(errorPassword)
                        Spacer(modifier = Modifier.height(8.dp))
                        RoundedTextButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.app_log_in),
                            showWaitIndicator = isLogInInProgress,
                            onClick = { authViewModel.logInUser(email = email, password = password) })
                        Error(errorLogIn)
                        Spacer(modifier = Modifier.height(8.dp))
                        RoundedOutlineTextButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.app_cancel),
                            onClick = { navManager.navigateUp() })
                    }
                }
            }
        }
    }
}