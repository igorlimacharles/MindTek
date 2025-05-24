package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.softtek.mindtek.data.model.AssessmentEntry
import br.com.fiap.softtek.mindtek.data.model.AssessmentViewModel

@Composable
fun AssessmentHistoryScreen(viewModel: AssessmentViewModel = viewModel()) {
    val history by viewModel.assessments.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAssessments()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Histórico de Avaliações", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(history) { entry: AssessmentEntry ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("Data: ${entry.data}", style = MaterialTheme.typography.labelMedium)
                    Text("Pergunta: ${entry.pergunta}")
                    Text("Resposta: ${if (entry.resposta) "Sim" else "Não"}")
                    Divider(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}
