package com.example.tcpm.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tcpm.navigation.data.NavManager

@Composable
fun ScreenUnauthenticated(
    navManager: NavManager,
    title: String,
    navigationIconType: NavigationIconType = NavigationIconType.NONE,
    onNavigationIconClicked: () -> Unit = {
        when (navigationIconType) {
            NavigationIconType.BACK -> navManager.navigateUp()
            NavigationIconType.NONE -> {}
            else -> throw Exception("unhandled onNavigationIconClicked in ScreenUnauthenticated")
        }
    },
    floatingActionButton: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {}
) {

    Scaffold(
        topBar = {
            TopAppBarView(
                title = title,
                navigationIconType = navigationIconType,
                onNavigationItemClicked = onNavigationIconClicked
            )
        },
        floatingActionButton = floatingActionButton,
        snackbarHost = snackbarHost,
        bottomBar = bottomBar
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            content()
        }
    }
}