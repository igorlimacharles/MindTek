package br.com.fiap.softtek.mindtek.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.softtek.mindtek.data.model.AssessmentEntry

@Dao
interface AssessmentDao {
    @Insert
    suspend fun inserirResposta(entry: AssessmentEntry)

    @Query("SELECT * FROM avaliacao ORDER BY id DESC")
    suspend fun getAll(): List<AssessmentEntry>
}

