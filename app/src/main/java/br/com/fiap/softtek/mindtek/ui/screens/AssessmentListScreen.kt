package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.softtek.mindtek.data.database.AppDatabase
import br.com.fiap.softtek.mindtek.data.model.AssessmentEntry
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AssessmentListScreen() {
    val context = LocalContext.current
    val dao = AppDatabase.getDatabase(context).assessmentDao()
    var respostas by remember { mutableStateOf(listOf<AssessmentEntry>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            respostas = dao.getAll()
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text("Respostas Gravadas", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        respostas.forEach { entry ->
            val horaFormatada = try {
                val formato24 = SimpleDateFormat("HH:mm", Locale.getDefault())
                val formato12 = SimpleDateFormat("hh:mm a", Locale.getDefault())
                formato12.format(formato24.parse(entry.hora) ?: entry.hora)
            } catch (e: Exception) {
                entry.hora
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Data: ${entry.data} às $horaFormatada")
                    Text("Pergunta: ${entry.pergunta}")
                    Text("Resposta: ${if (entry.resposta) "Sim" else "Não"}")
                }
            }
        }
    }
}

