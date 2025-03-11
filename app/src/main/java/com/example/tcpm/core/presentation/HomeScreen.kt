package com.example.tcpm.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.authentication.presentation.AuthenticationViewModel

@Composable
fun HomeScreen(navManager: NavManager, authViewModel: AuthenticationViewModel) {

    TCPMModalNavigationDrawer(navManager, authViewModel) {
        Scaffold(topBar = { TopAppBarView() }) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Text("Home Screen Dummy Text")
            }
        }
    }
}