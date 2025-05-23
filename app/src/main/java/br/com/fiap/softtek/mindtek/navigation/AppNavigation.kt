package br.com.fiap.softtek.mindtek.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.softtek.mindtek.data.mock.HistoryScreen
import br.com.fiap.softtek.mindtek.ui.screens.*
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("assessment") { AssessmentScreen(navController) }
        composable("checkin") { CheckInScreen(navController) }
        composable("graph") { GraphScreen(navController) }
        composable("resources") { ResourcesScreen(navController) }

        composable("history") {
            val context = LocalContext.current
            val viewModel: MoodHistoryViewModel = viewModel(
                factory = MoodHistoryViewModelFactory(context)
            )

            HistoryScreen(viewModel = viewModel)
        }

        composable("assessment_result") { AssessmentResultScreen() }

        composable("respostas") {
            AssessmentListScreen()
        }

        composable("assessmentHistory") {
            AssessmentHistoryScreen()
        }



    }
}

