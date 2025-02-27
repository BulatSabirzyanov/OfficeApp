package com.example.officeapp.presentation


import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.officeapp.presentation.auth.AuthScreen
import com.example.officeapp.presentation.main.MainScreen

@Composable
fun AppNavHost(viewModelFactory: ViewModelProvider.Factory, navController: NavHostController) {
    NavHost(navController, startDestination = "auth") {
        composable("auth") { AuthScreen(viewModelFactory, navController) }
        composable("main") { MainScreen(viewModelFactory, navController) }
    }
}