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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quickeng.ui.analyze.AnalyzeViewModel

@Composable
fun MainScaffold(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    // 뷰모델은 여기서 생성해서 HomeScreen에 전달 (화면이 전환되어도 유지됨)
    val vm: AnalyzeViewModel = viewModel()

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
        val vm: AnalyzeViewModel = viewModel()
        NavHost(
            navController = navController,
            startDestination = "splash", //스플래시 화면으로 진입
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Study.route) { SentenceListScreen() }
            // 1. 스플래시 스크린
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
            // 2. 학습 화면(학습한 카드 저장)
            composable(BottomNavItem.Study.route) { SentenceListScreen() }
            // 3. 홈화면
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    vm = vm,
                    onNavigateToScript = {
                        navController.navigate("script")
                    }
                )
            }
            // 4. 트래커 화면
            composable(BottomNavItem.Tracker.route) { TrackerScreen() }
            // 5. 스크립트 화면
            composable("script") { ScriptScreen() }


        }
    }
}

