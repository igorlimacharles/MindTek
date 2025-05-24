package br.com.fiap.softtek.mindtek.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.softtek.mindtek.data.repository.AssessmentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AssessmentViewModel : ViewModel() {

    private val repository = AssessmentRepository()

    private val _assessments = MutableStateFlow<List<AssessmentEntry>>(emptyList())
    val assessments: StateFlow<List<AssessmentEntry>> = _assessments

    fun loadAssessments() {
        viewModelScope.launch {
            try {
                val list = repository.getAssessments()
                _assessments.value = list
            } catch (e: Exception) {
                // Tratar erro
            }
        }
    }

    fun submitAssessment(assessment: AssessmentEntry) {
        viewModelScope.launch {
            try {
                repository.postAssessment(assessment)
                // Atualizar lista após envio
                loadAssessments()
            } catch (e: Exception) {
                // Tratar erro
            }
        }
    }
}
