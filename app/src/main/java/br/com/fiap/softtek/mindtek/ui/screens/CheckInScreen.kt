package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.softtek.mindtek.data.database.AppDatabase
import br.com.fiap.softtek.mindtek.data.model.MoodEntry
import br.com.fiap.softtek.mindtek.ui.components.PrimaryButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CheckInScreen(navController: NavController) {

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val moodDao = db.moodDao()
    
    var mood by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val entry = MoodEntry(
                    data = getTodayDate(),
                    emoji = mood,
                    observacao = notes
                )
                moodDao.inserirHumor(entry)

                withContext(Dispatchers.Main) {
                    navController.navigate("history")
                }
            }
        }) {
            Text("Salvar e Ver Gráfico")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("assessment_result")
        }) {
            Text("Ver Respostas da Avaliação")
        }

        PrimaryButton("Ver respostas") {
            navController.navigate("respostas")
        }




    }
}

fun getTodayDate(): String {
    val formatter = java.text.SimpleDateFormat("dd/MM", java.util.Locale.getDefault())
    return formatter.format(java.util.Date())
}

