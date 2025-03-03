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
    val authViewModel: AuthenticationViewModel = AuthenticationViewModel(navController)

    NavHost(
        navController = navController,
        startDestination = Screen.LoginRegisterScreen.route
    ) {
        composable(Screen.LoginRegisterScreen.route) {
            LoginRegisterScreen(navController, authViewModel)
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController, authViewModel)
        }
        composable(Screen.HomeScreen.route){
            HomeScreen()
        }
    }
}