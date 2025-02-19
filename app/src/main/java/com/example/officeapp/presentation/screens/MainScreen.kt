package com.example.officeapp.presentation.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.officeapp.presentation.NavItem

@Composable
fun MainScreen(navController: NavHostController) {


    val childNavController = rememberNavController() // Локальный контроллер

    val navItemList = listOf(
        NavItem("documents", Icons.Default.Home),
        NavItem("rooms", Icons.AutoMirrored.Filled.List),
        NavItem("trash", Icons.Default.Delete),
        NavItem("profile", Icons.Default.Person)
    )

    // Состояние для выбранного индекса
    val currentRoute = childNavController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentRoute == navItem.label,
                        onClick = {
                            childNavController.navigate(navItem.label) {
                                // Вместо обращения к childNavController.graph.startDestinationId,
                                // можно указать явно маршрут, например "documents":
                                popUpTo("documents") {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = null) },
                        label = { Text(navItem.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = childNavController,
            startDestination = "documents", // Начальный экран
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("documents") { DocumentsScreen() }
            composable("rooms") { RoomsScreen() }
            composable("trash") { TrashScreen() }
            composable("profile") { ProfileScreen { navController.navigate("auth") } }
        }
    }
}



