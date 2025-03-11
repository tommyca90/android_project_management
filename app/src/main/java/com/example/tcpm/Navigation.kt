package com.example.tcpm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tcpm.models.authentication.AuthenticationViewModel
import com.example.tcpm.screens.AccountScreen
import com.example.tcpm.screens.HomeScreen
import com.example.tcpm.screens.LoginRegisterScreen
import com.example.tcpm.screens.LoginScreen
import com.example.tcpm.screens.RegisterScreen

@Composable
fun Navigation(authViewModel: AuthenticationViewModel, navController: NavHostController = rememberNavController()) {
    val navManager = NavManager(navController)
    val navigateToHome by authViewModel.navigateToHome.collectAsState()
    val navigateToLoginRegister by authViewModel.navigateToLoginRegister.collectAsState()

    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            navManager.navigateToHome()
        }
    }

    LaunchedEffect(navigateToLoginRegister) {
        if (navigateToLoginRegister) {
            navManager.navigateToLoginRegister()
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.LoginRegisterScreen.route
    ) {
        composable(Screen.LoginRegisterScreen.route) {
            LoginRegisterScreen(navManager, authViewModel)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navManager, authViewModel)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navManager, authViewModel)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navManager, authViewModel)
        }
        composable(Screen.AccountScreen.route) {
            AccountScreen(navManager, authViewModel)
        }
    }
}