package br.com.fiap.softtek.mindtek.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CheckInScreen(navController: NavController) {
    var mood by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Como você se sente hoje?")
        OutlinedTextField(value = mood, onValueChange = { mood = it }, label = { Text("Emoção atual") })
        OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Observações") })
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("graph") }) {
            Text("Salvar e Ver Gráfico")
        }
    }
}