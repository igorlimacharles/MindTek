package br.com.fiap.softtek.mindtek.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "humor")
data class MoodEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val data: String,   // ex: "23/05"
    val hora: String,   // ex: "14:32"
    val emoji: String,  // ex: "😀"
    val observacao: String? = null
)


//@Entity(tableName = "mood_entries")
//data class MoodEntry(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,
//    val data: String,
//    val emoji: String,
//    val observacao: String
//)