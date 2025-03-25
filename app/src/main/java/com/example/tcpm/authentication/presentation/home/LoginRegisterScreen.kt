package com.example.tcpm.authentication.presentation.home

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.R
import com.example.tcpm.core.presentation.buttons.RoundedOutlineTextButton
import com.example.tcpm.core.presentation.buttons.RoundedTextButton
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.core.presentation.ScreenUnauthenticated

@Composable
fun LoginRegisterScreen(navManager: NavManager, authViewModel: AuthenticationViewModel) {

    val authenticatedUser by authViewModel.authUser.collectAsState()

    LaunchedEffect(authenticatedUser) {
        if(authenticatedUser.isAuthenticated){
            navManager.navigateToHome()
        }
    }

    ScreenUnauthenticated(
        navManager = navManager,
        title = stringResource(R.string.app_login_register)
    ) {
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
                onClick = { navManager.navigateToLogin() }
            )
            Spacer(modifier = Modifier.height(8.dp))
            RoundedOutlineTextButton(
                Modifier.fillMaxWidth(),
                text = stringResource(R.string.app_register),
                onClick = { navManager.navigateToRegister() }
            )
        }
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

