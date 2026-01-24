package com.example.quickeng.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.quickeng.ui.screen.HomeScreen
import com.example.quickeng.ui.screen.SplashScreen
import com.example.quickeng.ui.study.SentenceListScreen
import com.example.quickeng.ui.tracker.TrackerScreen
import com.example.quickeng.ui.script.ScriptScreen
import com.example.quickeng.ui.script.toSentenceUi
import com.example.quickeng.Routes // Routes 클래스가 있다면 임포트

@Composable
fun MainScaffold(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val studyVM: com.example.quickeng.viewmodel.StudyVM = androidx.lifecycle.viewmodel.compose.viewModel()

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
            // 상세 페이지("script/...")에서는 바텀바를 숨깁니다.
            val isBottomBarVisible = currentRoute != "splash" && currentRoute?.startsWith("script") == false

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
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            // 1. 홈 화면 (클릭 시 ID를 가지고 이동하도록 수정)
            composable(BottomNavItem.Home.route) {
                HomeScreen(onVideoClick = { videoId ->
                    navController.navigate("script/$videoId")
                })
            }

            // 2. 스크립트 화면 (ID를 인자로 받도록 수정)
            composable(
                route = "script/{videoId}",
                arguments = listOf(navArgument("videoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val videoId = backStackEntry.arguments?.getInt("videoId") ?: 1
                ScriptScreen(
                    videoId = videoId,
                    studyVM = studyVM,
                    onAddClick = { selectedItems ->
                        studyVM.addSentences(selectedItems.map { it.toSentenceUi() })
                        // 추가 후 스터디 리스트 화면으로 이동
                        navController.navigate(BottomNavItem.Study.route)
                    }
                )
            }

            // 3. 스터디 리스트 화면
            composable(BottomNavItem.Study.route) {
                val sentenceItems by studyVM.sentences.collectAsState()
                SentenceListScreen(
                    items = sentenceItems,
                    onDelete = { id -> studyVM.deleteSentence(id) }
                )
            }

            composable("splash") {
                SplashScreen(onTimeout = {
                    navController.navigate(BottomNavItem.Home.route) {
                        popUpTo("splash") { inclusive = true }
                    }
                })
            }

            composable(BottomNavItem.Tracker.route) { TrackerScreen() }
        }
    }
}