package br.com.fiap.softtek.mindtek.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.softtek.mindtek.data.database.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.content.Context

class MoodHistoryViewModel(private val context: Context) : ViewModel() {
    private val moodDao = AppDatabase.getDatabase(context).moodDao()

    private val _moodList = MutableStateFlow<List<MoodEntry>>(emptyList())
    val moodList: StateFlow<List<MoodEntry>> = _moodList

    fun loadMoodHistory() {
        viewModelScope.launch {
            val list = moodDao.getAll() // ou listarHumores, se esse for o nome certo
            _moodList.value = list
        }
    }
}

//class MoodHistoryViewModel(private val context: Context) : ViewModel() {
//
//    private val moodDao = AppDatabase.getDatabase(context).moodDao()
//
//    private val _moodList = MutableStateFlow<List<MoodEntry>>(emptyList())
//    val moodList: StateFlow<List<MoodEntry>> = _moodList
//
//    fun loadMoodHistory() {
//        viewModelScope.launch {
//            val list = moodDao.listarHumores()
//            _moodList.value = list
//        }
//    }
//}