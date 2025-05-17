package br.com.fiap.softtek.mindtek.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoodSelector(selected: String?, onMoodSelected: (String) -> Unit) {
    val moods = listOf("😀", "😐", "😢", "😠", "😨")

    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        moods.forEach { emoji ->
            Text(
                text = emoji,
                fontSize = 32.sp,
                modifier = Modifier
                    .clickable { onMoodSelected(emoji) }
                    .padding(8.dp)
            )
        }
    }
}