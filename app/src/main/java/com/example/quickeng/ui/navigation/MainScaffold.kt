package com.example.quickeng.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.quickeng.ui.screen.HomeScreen
import com.example.quickeng.ui.screen.StudyScreen
import com.example.quickeng.ui.screen.TrackerScreen

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Study,
        BottomNavItem.Home,
        BottomNavItem.Tracker
    )

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            BottomBar(
                items = items,
                currentRoute = currentRoute,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Study.route) { StudyScreen() }
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Tracker.route) { TrackerScreen() }
        }
    }
}

