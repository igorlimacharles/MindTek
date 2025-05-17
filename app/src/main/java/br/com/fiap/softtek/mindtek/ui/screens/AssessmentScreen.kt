package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import br.com.fiap.softtek.mindtek.data.mock.MockAssessment
import br.com.fiap.softtek.mindtek.ui.components.PrimaryButton

@Composable
fun AssessmentScreen(navController: NavController) {
    val questions = MockAssessment.questions
    val answers = remember { mutableStateMapOf<Int, Boolean>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Avaliação Psicossocial", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        questions.forEach { question ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(question.text, modifier = Modifier.weight(1f))
                Switch(
                    checked = answers[question.id] ?: false,
                    onCheckedChange = { answers[question.id] = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton("Próximo") {
            navController.navigate("checkin")
        }
    }
}