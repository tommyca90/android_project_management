package com.example.tcpm.navigation.data

import androidx.navigation.NavController
import com.example.tcpm.core.presentation.Screen

class NavManager(private val navController: NavController) {
    fun navigateToLoginRegister() {
        navController.navigate(Screen.LoginRegisterScreen.route) {
            popUpTo(0) { inclusive = true }
        }
    }

    fun navigateToLogin() {
        navController.navigate(Screen.LoginScreen.route)
    }

    fun navigateToRegister() {
        navController.navigate(Screen.RegisterScreen.route)
    }

    fun navigateToHome() {
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(0) { inclusive = true }
        }
    }

    fun navigateToAccount() {
        navController.navigate(Screen.AccountScreen.route){
            popUpTo(0){ inclusive = true }
        }
    }

    fun navigateUp() {
        navController.navigateUp()
    }
}