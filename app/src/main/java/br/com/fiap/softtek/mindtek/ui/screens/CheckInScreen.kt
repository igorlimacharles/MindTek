package br.com.fiap.softtek.mindtek.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.softtek.mindtek.data.database.AppDatabase
import br.com.fiap.softtek.mindtek.data.model.MoodEntry
import br.com.fiap.softtek.mindtek.ui.components.PrimaryButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun CheckInScreen(navController: NavController) {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val moodDao = db.moodDao()

    var selectedMood by remember { mutableStateOf<String?>(null) }
    var notes by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val moods = listOf("😀", "😐", "😞", "😡", "😴") // emojis para humor, pode ajustar

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Como você está se sentindo agora?", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            moods.forEach { emoji ->
                Text(
                    text = emoji,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .clickable { selectedMood = emoji }
                        .background(
                            if (selectedMood == emoji) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                            else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Observações (opcional)") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(24.dp))


        PrimaryButton(
            text = "Salvar",
            enabled = selectedMood != null
        ) {
            if (selectedMood == null) {
                Toast.makeText(context, "Por favor, selecione um sentimento", Toast.LENGTH_SHORT).show()
                return@PrimaryButton
            }

            coroutineScope.launch(Dispatchers.IO) {
                val dataHoje = SimpleDateFormat("dd/MM", Locale.getDefault()).format(Date())
                val horaAgora = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

                val entry = MoodEntry(
                    data = dataHoje,
                    hora = horaAgora,
                    emoji = selectedMood!!,
                    observacao = if (notes.isBlank()) null else notes
                )
                moodDao.inserirHumor(entry)

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Sentimento salvo!", Toast.LENGTH_SHORT).show()
                    navController.navigate("history")
                }
            }
        }

    }
}
