package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.softtek.mindtek.data.mock.MockResources
import br.com.fiap.softtek.mindtek.ui.components.PrimaryButton
import br.com.fiap.softtek.mindtek.ui.components.ResourceCard

@Composable
fun ResourcesScreen(navController: NavController) {
    val resources = MockResources.list

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Recursos de Apoio", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        resources.forEach {
            ResourceCard(title = it.title, description = it.description)
        }

        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton("Voltar ao Início") {
            navController.navigate("welcome")
        }
    }
}