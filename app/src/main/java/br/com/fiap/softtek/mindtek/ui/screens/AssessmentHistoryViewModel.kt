package br.com.fiap.softtek.mindtek.ui.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.fiap.softtek.mindtek.data.database.AppDatabase
import br.com.fiap.softtek.mindtek.data.model.AssessmentEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AssessmentHistoryViewModel(private val context: Context) : ViewModel() {

    private val assessmentDao = AppDatabase.getDatabase(context).assessmentDao()

    private val _assessmentList = MutableStateFlow<List<AssessmentEntry>>(emptyList())
    val assessmentList: StateFlow<List<AssessmentEntry>> = _assessmentList

    fun loadAssessmentHistory() {
        viewModelScope.launch {
            val list = assessmentDao.getAll()
            _assessmentList.value = list
        }
    }
}

class AssessmentHistoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AssessmentHistoryViewModel(context) as T
    }
}


