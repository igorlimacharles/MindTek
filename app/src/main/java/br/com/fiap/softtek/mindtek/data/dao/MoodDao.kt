package br.com.fiap.softtek.mindtek.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.softtek.mindtek.data.model.MoodEntry

@Dao
interface MoodDao {

    @Insert
    suspend fun inserirHumor(moodEntry: MoodEntry)

    @Query("SELECT * FROM humor ORDER BY data DESC, hora DESC")
    suspend fun getAll(): List<MoodEntry>
}
