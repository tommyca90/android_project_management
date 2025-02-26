package com.example.tcpm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tcpm.screens.LoginRegisterScreen
import com.example.tcpm.screens.RegisterScreen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginRegisterScreen.route
    ) {
        composable(Screen.LoginRegisterScreen.route) {
            LoginRegisterScreen(navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController)
        }
    }
}