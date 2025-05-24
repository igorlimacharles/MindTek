package br.com.fiap.softtek.mindtek.br.com.fiap.softtek.mindtek.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Início", "welcome", Icons.Default.Home),
        BottomNavItem("Avaliação", "assessment", Icons.Default.List),
        BottomNavItem("Check-in", "checkin", Icons.Default.EmojiEmotions),
        BottomNavItem("Histórico", "history", Icons.Default.PieChart),
        BottomNavItem("Recursos", "resources", Icons.Default.Lightbulb)
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                }
            )
        }
    }
}
