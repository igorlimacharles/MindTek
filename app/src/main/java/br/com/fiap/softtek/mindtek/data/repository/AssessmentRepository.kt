package br.com.fiap.softtek.mindtek.data.repository

import br.com.fiap.softtek.mindtek.data.model.*
import br.com.fiap.softtek.mindtek.data.network.RetrofitInstance

class AssessmentRepository {

    private val api = RetrofitInstance.api

    suspend fun getAssessments(): List<AssessmentEntry> {
        return api.getAssessments()
    }

    suspend fun postAssessment(assessment: AssessmentEntry) {
        api.postAssessment(assessment)
    }
}
