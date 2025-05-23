package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AssessmentHistoryScreen() {
    val context = LocalContext.current
    val viewModel: AssessmentHistoryViewModel = viewModel(
        factory = AssessmentHistoryViewModelFactory(context)
    )
    val history by viewModel.assessmentList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAssessmentHistory()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Histórico de Avaliações", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(history) { entry ->
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
