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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.officeapp.presentation.NavItem
import com.example.officeapp.utils.Backpressed

@Composable
fun MainScreen(
    viewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController
) {
    val childNavController = rememberNavController()

    val navItemList = listOf(
        NavItem("documents", Icons.Default.Home),
        NavItem("rooms", Icons.AutoMirrored.Filled.List),
        NavItem("trash", Icons.Default.Delete),
        NavItem("profile", Icons.Default.Person)
    )

    val currentRoute = childNavController.currentBackStackEntryAsState().value?.destination?.route

    val doubleBackToExitPressedOnce = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Backpressed(context, doubleBackToExitPressedOnce)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEach { navItem ->
                    val isSelected = if (navItem.label == "documents") {
                        currentRoute == "documents" || currentRoute?.startsWith("documentDetail") == true
                    } else {
                        currentRoute == navItem.label
                    }
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            childNavController.navigate(navItem.label) {
                                popUpTo("documents") { saveState = true }
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
            startDestination = "documents",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("documents") {
                DocumentsScreen(
                    onDocumentClick = { docId ->
                        childNavController.navigate("documentDetail/$docId")
                    }
                )
            }
            composable("rooms") { RoomsScreen() }
            composable("trash") { TrashScreen() }
            composable("profile") {
                ProfileScreen(viewModelFactory) {
                    navController.navigate("auth") {
                        popUpTo("auth") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
            composable(
                route = "documentDetail/{docId}",
                arguments = listOf(navArgument("docId") { type = NavType.StringType })
            ) { backStackEntry ->
                val docId = backStackEntry.arguments?.getString("docId")
                DocumentDetailScreen(
                    navController = childNavController,
                    docId = docId
                )
            }
        }
    }
}
