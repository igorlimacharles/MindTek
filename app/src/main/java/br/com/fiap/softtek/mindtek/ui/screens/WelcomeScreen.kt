package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.softtek.mindtek.ui.components.PrimaryButton

@Composable
fun WelcomeScreen(navController: NavController) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedVisibility(visible = visible) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Bem-vindo ao App Softtek!", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Promovendo saúde mental e bem-estar.")
                Spacer(modifier = Modifier.height(24.dp))
                PrimaryButton("Começar") {
                    navController.navigate("assessment")
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}