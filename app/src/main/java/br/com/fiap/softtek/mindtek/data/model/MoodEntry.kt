package br.com.fiap.softtek.mindtek.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_entries")
data class MoodEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val data: String,
    val emoji: String,
    val observacao: String
)

