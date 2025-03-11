package com.example.tcpm

import androidx.navigation.NavController

class NavManager(private val navController: NavController) {
    fun navigateToLoginRegister(){
        navController.navigate(Screen.LoginRegisterScreen.route)
    }
    fun navigateToLogin() {
        navController.navigate(Screen.LoginScreen.route)
    }
    fun navigateToRegister(){
        navController.navigate(Screen.RegisterScreen.route)
    }
    fun navigateToHome(){
        navController.navigate(Screen.HomeScreen.route)
    }
    fun navigateToAccount(){
        navController.navigate(Screen.AccountScreen.route)
    }
    fun navigateUp(){
        navController.navigateUp()
    }
}