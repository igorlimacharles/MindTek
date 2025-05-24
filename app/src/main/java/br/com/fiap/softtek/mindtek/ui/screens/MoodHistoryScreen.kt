package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.fiap.softtek.mindtek.data.model.MoodEntry
import br.com.fiap.softtek.mindtek.data.model.MoodHistoryViewModel
import br.com.fiap.softtek.mindtek.data.model.MoodHistoryViewModelFactory
import br.com.fiap.softtek.mindtek.ui.components.PrimaryButton
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MoodHistoryScreen(navController: NavHostController) {
    val context = LocalContext.current

    val viewModel: MoodHistoryViewModel = viewModel(
        factory = MoodHistoryViewModelFactory(context)
    )

    val moods by viewModel.moodList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMoodHistory()
    }

    Column(modifier = Modifier.padding(16.dp)) {

        PrimaryButton("Preencher Check-In") {
            navController.navigate("checkin")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(moods) { moodEntry ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        //Text("Data: ${moodEntry.data} às ${moodEntry.hora}")
                        val horaFormatada = try {
                            val formato24 = SimpleDateFormat("HH:mm", Locale.getDefault())
                            val formato12 = SimpleDateFormat("hh:mm a", Locale.getDefault())
                            formato12.format(formato24.parse(moodEntry.hora) ?: moodEntry.hora)
                        } catch (e: Exception) {
                            moodEntry.hora
                        }

                        Text("Data: ${moodEntry.data} às $horaFormatada")

                        Text("Sentimento: ${moodEntry.emoji}", fontSize = 24.sp)
                        if (!moodEntry.observacao.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Observação: ${moodEntry.observacao}")
                        }
                    }
                }
            }
        }
    }
}