package br.com.fiap.softtek.mindtek.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.softtek.mindtek.data.model.MoodEntry

@Dao
interface MoodDao {
    @Insert
    suspend fun inserirHumor(entry: MoodEntry)

    @Query("SELECT * FROM mood_entries ORDER BY id DESC")
    suspend fun listarHumores(): List<MoodEntry>


}