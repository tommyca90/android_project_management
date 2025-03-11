package com.example.tcpm

import androidx.navigation.NavController

class NavManager(private val navController: NavController) {
    fun navigateToLogin() {
        navController.navigate(Screen.LoginScreen.route)
    }
    fun navigateToRegister(){
        navController.navigate(Screen.RegisterScreen.route)
    }
    fun navigateToHome(){
        navController.navigate(Screen.HomeScreen.route)
    }
    fun navigateUp(){
        navController.navigateUp()
    }
}