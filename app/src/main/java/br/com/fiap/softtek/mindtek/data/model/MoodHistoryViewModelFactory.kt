package br.com.fiap.softtek.mindtek.data.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//class MoodHistoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MoodHistoryViewModel::class.java)) {
//            return MoodHistoryViewModel(context) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}


class MoodHistoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodHistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoodHistoryViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

