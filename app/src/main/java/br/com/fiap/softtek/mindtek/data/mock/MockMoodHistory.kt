package br.com.fiap.softtek.mindtek.data.mock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.softtek.mindtek.data.model.MoodHistoryViewModel

@Composable
fun HistoryScreen(viewModel: MoodHistoryViewModel) {
    val moodList by viewModel.moodList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMoodHistory()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Histórico de Humor")
        Spacer(modifier = Modifier.height(8.dp))

        if (moodList.isEmpty()) {
            Text("Nenhum registro encontrado.")
        } else {
            moodList.forEach { entry ->
                Text("${entry.data}: ${entry.emoji} - ${entry.observacao}")
            }
        }
    }
}

