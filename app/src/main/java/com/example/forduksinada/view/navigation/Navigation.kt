package com.example.forduksinada.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        /**
         *  экран приветствия
         */
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        /**
         * центральный экран
         */
        composable("main_screen") {
            MainScreen(navController = navController)
        }
    }
}



