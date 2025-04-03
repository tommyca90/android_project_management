package com.example.tcpm.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.core.presentation.Screen
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.user.presentation.AccountScreen
import com.example.tcpm.core.presentation.HomeScreen
import com.example.tcpm.authentication.presentation.home.LoginRegisterScreen
import com.example.tcpm.authentication.presentation.login.LoginScreen
import com.example.tcpm.authentication.presentation.register.RegisterScreen
import com.example.tcpm.project.presentation.AddProjectScreen
import com.example.tcpm.project.presentation.AddProjectViewModel

val authenticationRoutes = listOf(
    Screen.LoginScreen.route,
    Screen.RegisterScreen.route
)

@Composable
fun Navigation(
    authViewModel: AuthenticationViewModel,
    addProjectViewModel: AddProjectViewModel,
    navController: NavHostController = rememberNavController()
) {
    val navManager = NavManager(navController)
    val authUser by authViewModel.authUser.collectAsState()

    LaunchedEffect(authUser) {
        val currentRoute = navController.currentDestination?.route.toString()

        if (authUser.isAuthenticated) {
            // redirect after successful authentication
            if (currentRoute in authenticationRoutes
                || currentRoute == Screen.AuthLoadingScreen.route
            ) {
                navManager.navigateToHome()
            }
        } else {
            // redirect once it is known that the user is unauthorized
            if (currentRoute == Screen.AuthLoadingScreen.route
                || currentRoute !in authenticationRoutes
            ) {
                navManager.navigateToLoginRegister()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.AuthLoadingScreen.route
    ) {
        composable(Screen.AuthLoadingScreen.route) {}
        composable(Screen.LoginRegisterScreen.route) {
            LoginRegisterScreen(navManager)
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
        composable(Screen.AddProject.route) {
            AddProjectScreen(
                navManager = navManager,
                authViewModel = authViewModel,
                addProjectViewModel = addProjectViewModel
            )
        }
    }
}

