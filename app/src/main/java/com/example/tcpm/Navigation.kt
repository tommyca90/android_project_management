package com.example.tcpm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tcpm.models.authentication.AuthenticationViewModel
import com.example.tcpm.screens.HomeScreen
import com.example.tcpm.screens.LoginRegisterScreen
import com.example.tcpm.screens.LoginScreen
import com.example.tcpm.screens.RegisterScreen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    val authViewModel = AuthenticationViewModel(navController)
    val navManager = NavManager(navController)

    NavHost(
        navController = navController,
        startDestination = Screen.LoginRegisterScreen.route
    ) {
        composable(Screen.LoginRegisterScreen.route) {
            LoginRegisterScreen(navManager, authViewModel)
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(navManager, authViewModel)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navManager, authViewModel)
        }
        composable(Screen.HomeScreen.route){
            HomeScreen(authViewModel)
        }
    }
}