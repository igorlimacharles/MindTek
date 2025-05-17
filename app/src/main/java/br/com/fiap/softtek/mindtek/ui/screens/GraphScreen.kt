package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.softtek.mindtek.data.mock.MockMoodHistory
import br.com.fiap.softtek.mindtek.ui.components.PrimaryButton

@Composable
fun GraphScreen(navController: NavController) {
    val moodHistory = MockMoodHistory.history

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Seu histórico de humor", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        moodHistory.forEach {
            Text("${it.day}: ${it.mood}", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton("Recursos de Apoio") {
            navController.navigate("resources")
        }
    }
}