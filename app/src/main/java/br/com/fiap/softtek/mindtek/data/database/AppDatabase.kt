package br.com.fiap.softtek.mindtek.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.softtek.mindtek.data.dao.MoodDao
import br.com.fiap.softtek.mindtek.data.model.MoodEntry

@Database(entities = [MoodEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moodDao(): MoodDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mindtek_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}