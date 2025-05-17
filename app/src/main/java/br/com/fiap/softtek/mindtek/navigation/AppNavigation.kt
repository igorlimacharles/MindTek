package br.com.fiap.softtek.mindtek.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.softtek.mindtek.ui.screens.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("assessment") { AssessmentScreen(navController) }
        composable("checkin") { CheckInScreen(navController) }
        composable("graph") { GraphScreen(navController) }
        composable("resources") { ResourcesScreen(navController) }
    }
}