package com.example.tcpm

sealed class Screen(val route: String) {
    data object LoginRegisterScreen: Screen("login_register_screen")
}