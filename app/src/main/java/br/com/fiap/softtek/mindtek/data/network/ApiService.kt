package br.com.fiap.softtek.mindtek.data.network
import br.com.fiap.softtek.mindtek.data.model.*

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ApiService {

    @GET("assessments")
    suspend fun getAssessments(): List<AssessmentEntry>

    @POST("assessments")
    suspend fun postAssessment(@Body assessment: AssessmentEntry)

    @GET("moods")
    suspend fun getMoods(): List<MoodEntry>

    @POST("moods")
    suspend fun postMood(@Body mood: MoodEntry)

    // Adicione outros endpoints conforme necessário
}
