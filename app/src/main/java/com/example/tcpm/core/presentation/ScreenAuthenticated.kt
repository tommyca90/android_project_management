package com.example.tcpm.core.presentation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import kotlinx.coroutines.launch

@Composable
fun ScreenAuthenticated(
    navManager: NavManager,
    authViewModel: AuthenticationViewModel,
    title: String,
    navigationIconType: NavigationIconType,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val drawerState = DrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    TCPMModalNavigationDrawer(
        navManager = navManager,
        authViewModel = authViewModel,
        drawerState = drawerState
    ) {
        ScreenUnauthenticated(
            navManager = navManager,
            title = title,
            navigationIconType = navigationIconType,
            onNavigationIconClicked = {
                when (navigationIconType) {
                    NavigationIconType.DRAWER -> scope.launch {drawerState.open()}
                    NavigationIconType.BACK -> navManager.navigateUp()
                    NavigationIconType.NONE -> {}
                }
            },
            floatingActionButton = floatingActionButton
        ) {
            content()
        }
    }
}