package com.example.quickeng.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.quickeng.ui.screen.HomeScreen
import com.example.quickeng.ui.screen.SplashScreen
import com.example.quickeng.ui.study.SentenceListScreen
import com.example.quickeng.ui.tracker.TrackerScreen
import com.example.quickeng.ui.script.ScriptScreen

@Composable
fun MainScaffold(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Study,
        BottomNavItem.Home,
        BottomNavItem.Tracker
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // 1. 바텀바를 숨겨야 하는 화면들을 정의 (스플래시 OR 스크립트 화면)
            val isBottomBarVisible = currentRoute != "splash" && currentRoute != "script"

            if (isBottomBarVisible) {
                BottomBar(
                    items = items,
                    currentRoute = currentRoute,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash", //스플래시 화면으로 진입
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Study.route) { SentenceListScreen() }
            // 스플래시 스크린
            composable("splash") {
                SplashScreen(
                    onTimeout = {
                        // 홈으로 이동 + 뒤로 가기 막기 로직
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                )
            }
            composable(BottomNavItem.Study.route) { SentenceListScreen() }
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    onVideoClick = { navController.navigate("script") }
                )
            }
            composable(BottomNavItem.Tracker.route) { TrackerScreen() }
            composable("script") { ScriptScreen() }


        }
    }
}

