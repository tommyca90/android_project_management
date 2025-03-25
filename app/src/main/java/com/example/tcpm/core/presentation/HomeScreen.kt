package com.example.tcpm.core.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.R

@Composable
fun HomeScreen(navManager: NavManager, authViewModel: AuthenticationViewModel) {
    ScreenAuthenticated(
        navManager = navManager,
        authViewModel = authViewModel,
        title = stringResource(R.string.title_user_projects),
        navigationIconType = NavigationIconType.DRAWER,
        content = {
            Text("Home Screen Dummy Text")
        }
    )
}