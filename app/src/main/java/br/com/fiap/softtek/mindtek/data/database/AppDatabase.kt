package br.com.fiap.softtek.mindtek.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.softtek.mindtek.data.dao.MoodDao
import br.com.fiap.softtek.mindtek.data.dao.AssessmentDao
import br.com.fiap.softtek.mindtek.data.model.MoodEntry
import br.com.fiap.softtek.mindtek.data.model.AssessmentEntry

@Database(entities = [MoodEntry::class, AssessmentEntry::class], version = 4)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moodDao(): MoodDao

    abstract fun assessmentDao(): AssessmentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mindtek_db"
                )
                    // ⚠️ Remove esta linha em produção. Ela evita crashes por mudança de schema.
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
