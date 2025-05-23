package br.com.fiap.softtek.mindtek.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avaliacao")
data class AssessmentEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val data: String,
    val perguntaId: Int,
    val pergunta: String,
    val resposta: Boolean
)

