package com.example.tcpm.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tcpm.TopAppBarView
import com.example.tcpm.composables.TCPMModalNavigationDrawer
import com.example.tcpm.models.authentication.AuthenticationViewModel

@Composable
fun HomeScreen(authViewModel: AuthenticationViewModel) {

    TCPMModalNavigationDrawer(authViewModel) {
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