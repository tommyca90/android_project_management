package com.example.tcpm.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tcpm.R
import com.example.tcpm.TopAppBarView
import com.example.tcpm.composables.buttons.RoundedOutlineTextButton
import com.example.tcpm.composables.buttons.RoundedTextButton
import com.example.tcpm.composables.inputs.OutlinedPasswordField
import com.example.tcpm.models.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController, registerViewModel: RegisterViewModel = viewModel()) {
    val username by registerViewModel.username
    val email by registerViewModel.email
    val password by registerViewModel.password
    val passwordRepetition by registerViewModel.passwordRepetition

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBarView(title = "Register", true, onBackNavClicked = { navController.navigateUp() })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp).verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.app_register_intro)
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
                            value = username,
                            onValueChange = { registerViewModel.onUsernameChanged(it) },
                            label = { Text(stringResource(R.string.app_username)) },
                            colors = configuredColors)
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = modifierOutlinedTextFields,
                            value = email,
                            onValueChange = { registerViewModel.onEmailChanged(it) },
                            label = { Text(stringResource(R.string.app_email)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = configuredColors
                            )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedPasswordField(
                            modifier = modifierOutlinedTextFields,
                            value = password,
                            onValueChange = { registerViewModel.onPasswordChanged(it) },
                            label = stringResource(R.string.app_pwd),
                            colors = configuredColors
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedPasswordField(
                            modifier = modifierOutlinedTextFields,
                            value = passwordRepetition,
                            onValueChange = { registerViewModel.onPasswordRepetitionChanged(it) },
                            label = stringResource(R.string.app_pwd_repeat),
                            colors = configuredColors
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        RoundedTextButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.app_register),
                            onClick = { registerViewModel.registerUser() })
                        Spacer(modifier = Modifier.height(8.dp))
                        RoundedOutlineTextButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.app_cancel),
                            onClick = { navController.navigateUp() })
                    }
                }
            }
        }
    }
}