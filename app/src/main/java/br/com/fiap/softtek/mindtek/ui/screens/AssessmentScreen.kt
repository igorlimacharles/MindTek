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
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.softtek.mindtek.data.database.AppDatabase
import br.com.fiap.softtek.mindtek.data.model.AssessmentEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


import androidx.compose.ui.graphics.Color

@Composable
fun AssessmentScreen(navController: NavController) {
    val questions = MockAssessment.questions
    val answers = remember { mutableStateMapOf<Int, Boolean>() }
    val context = LocalContext.current
    val dao = AppDatabase.getDatabase(context).assessmentDao()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Avaliação Psicossocial", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        questions.forEach { question ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(question.text, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { answers[question.id] = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (answers[question.id] == true)
                                MaterialTheme.colorScheme.primary
                            else
                                Color.Gray.copy(alpha = 0.6f)
                        )
                    ) {
                        Text("Sim")
                    }
                    Button(
                        onClick = { answers[question.id] = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (answers[question.id] == false)
                                MaterialTheme.colorScheme.primary
                            else
                                Color.Gray.copy(alpha = 0.6f)
                        )
                    ) {
                        Text("Não")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton("Salvar") {
            coroutineScope.launch(Dispatchers.IO) {
                val dataHoje = SimpleDateFormat("dd/MM", Locale.getDefault()).format(Date())
                val horaAgora = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()) // ⬅️ hora adicionada aqui

                MockAssessment.questions.forEach { pergunta ->
                    val resposta = answers[pergunta.id]
                    if (resposta != null) {
                        dao.inserirResposta(
                            AssessmentEntry(
                                data = dataHoje,
                                hora = horaAgora, // ⬅️ nova linha adicionada
                                perguntaId = pergunta.id,
                                pergunta = pergunta.text,
                                resposta = resposta
                            )
                        )
                    }
                }

                withContext(Dispatchers.Main) {
                    navController.navigate("assessment")
                }
            }
        }

        PrimaryButton("Ver respostas") {
            navController.navigate("respostas")
        }
    }
}