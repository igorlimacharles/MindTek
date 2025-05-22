package br.com.fiap.softtek.mindtek.data.mock

data class MockMood(val day: String, val mood: String)

object MockMoodHistory {
    val history = listOf(
        MockMood("01/05", "😃"),
        MockMood("02/05", "😞"),
        MockMood("03/05", "😐"),
        MockMood("04/05", "😊"),
        MockMood("05/05", "😴")
    )
}
