package br.com.fiap.softtek.mindtek.data.mock

data class ResourceItem(val title: String, val description: String)

object MockResources {
    val list = listOf(
        ResourceItem("Terapia Online", "Conecte-se com psicólogos licenciados."),
        ResourceItem("Grupos de Apoio", "Participe de encontros semanais."),
        ResourceItem("Mindfulness", "Práticas de atenção plena e meditação."),
        ResourceItem("Ginástica Laboral", "Movimente-se com sessões guiadas.")
    )
}