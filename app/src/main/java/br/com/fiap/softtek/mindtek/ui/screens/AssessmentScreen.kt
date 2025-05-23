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
            coroutineScope.launch(Dispatchers.IO) {
                val dataHoje = SimpleDateFormat("dd/MM", Locale.getDefault()).format(Date())

                MockAssessment.questions.forEach { pergunta ->
                    val resposta = answers[pergunta.id]
                    if (resposta != null) {
                        dao.inserirResposta(
                            AssessmentEntry(
                                data = dataHoje,
                                perguntaId = pergunta.id,
                                pergunta = pergunta.text,
                                resposta = resposta
                            )
                        )
                    }
                }

                withContext(Dispatchers.Main) {
                    navController.navigate("checkin")
                }
            }
        }

//        PrimaryButton("Ver Histórico") {
//            navController.navigate("assessmentHistory")
//        }

    }
}



//@Composable
//fun AssessmentScreen(navController: NavController) {
//    val questions = MockAssessment.questions
//    val answers = remember { mutableStateMapOf<Int, Boolean>() }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text("Avaliação Psicossocial", style = MaterialTheme.typography.headlineSmall)
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        questions.forEach { question ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(question.text, modifier = Modifier.weight(1f))
//                Switch(
//                    checked = answers[question.id] ?: false,
//                    onCheckedChange = { answers[question.id] = it }
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
////        PrimaryButton("Próximo") {
////            navController.navigate("checkin")
////        }
//
//        val context = LocalContext.current
//        val dao = AppDatabase.getDatabase(context).assessmentDao()
//
//        PrimaryButton("Próximo") {
//            CoroutineScope(Dispatchers.IO).launch {
//                val dataHoje = SimpleDateFormat("dd/MM", Locale.getDefault()).format(Date())
//
//                MockAssessment.questions.forEach { pergunta ->
//                    val resposta = answers[pergunta.id]
//                    if (resposta != null) {
//                        dao.inserirResposta(
//                            AssessmentEntry(
//                                data = dataHoje,
//                                perguntaId = pergunta.id,
//                                pergunta = pergunta.text,
//                                resposta = resposta
//                            )
//                        )
//                    }
//                }
//
//                withContext(Dispatchers.Main) {
//                    navController.navigate("checkin")
//                }
//            }
//        }
//
//
//    }
//}