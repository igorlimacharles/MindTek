package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.softtek.mindtek.data.database.AppDatabase
import br.com.fiap.softtek.mindtek.data.model.AssessmentEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AssessmentResultScreen() {
    val context = LocalContext.current
    val dao = AppDatabase.getDatabase(context).assessmentDao()
    val scope = rememberCoroutineScope()

    var respostas by remember { mutableStateOf<List<AssessmentEntry>>(emptyList()) }

    LaunchedEffect(Unit) {
        scope.launch {
            respostas = withContext(Dispatchers.IO) { dao.getAll() }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Respostas da Avaliação", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        if (respostas.isEmpty()) {
            Text("Nenhuma resposta registrada.")
        } else {
            respostas.forEach {
                Text("${it.pergunta} — Resposta: ${if (it.resposta) "Sim" else "Não"}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
