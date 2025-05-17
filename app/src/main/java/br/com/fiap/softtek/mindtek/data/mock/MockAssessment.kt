package br.com.fiap.softtek.mindtek.data.mock

data class AssessmentQuestion(val id: Int, val text: String)

object MockAssessment {
    val questions = listOf(
        AssessmentQuestion(1, "Você sente que tem metas excessivas?"),
        AssessmentQuestion(2, "Sua jornada de trabalho é extensa?"),
        AssessmentQuestion(3, "Você sente falta de suporte da equipe?"),
        AssessmentQuestion(4, "Já passou por situações de assédio moral?"),
        AssessmentQuestion(5, "Consegue equilibrar trabalho e vida pessoal?")
    )
}