package br.com.fiap.softtek.mindtek.data.mock

data class MoodEntry(val day: String, val mood: String)

object MockMoodHistory {
    val history = listOf(
        MoodEntry("01/05", "😀"),
        MoodEntry("02/05", "😐"),
        MoodEntry("03/05", "😢"),
        MoodEntry("04/05", "😠"),
        MoodEntry("05/05", "😀")
    )
}