package com.example.tcpm.core.presentation

sealed class Screen(val route: String) {
    data object AuthLoadingScreen: Screen("auth_loading_screen")
    data object LoginRegisterScreen: Screen("login_register_screen")
    data object LoginScreen: Screen("login_screen")
    data object RegisterScreen: Screen("register_screen")
    data object HomeScreen: Screen("home_screen")
    data object AccountScreen: Screen("account_screen")
}