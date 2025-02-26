package com.example.tcpm.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tcpm.R
import com.example.tcpm.Screen
import com.example.tcpm.composables.buttons.RoundedOutlineTextButton
import com.example.tcpm.composables.buttons.RoundedTextButton

@Composable
fun LoginRegisterScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Intro()
        Spacer(modifier = Modifier.height(8.dp))
        RoundedTextButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.app_log_in),
            onClick = { navController.navigate(Screen.LoginScreen.route) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        RoundedOutlineTextButton(
            Modifier.fillMaxWidth(),
            text = stringResource(R.string.app_register),
            onClick = { navController.navigate(Screen.RegisterScreen.route) }
        )
    }
}

@Composable
fun Intro() {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.app_intro_title),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedCard(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Intro Image")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.app_intro_sub_title),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.app_intro_body), style = MaterialTheme.typography.bodyMedium
        )
    }
}

