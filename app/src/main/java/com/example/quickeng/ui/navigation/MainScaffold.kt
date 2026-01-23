package com.example.quickeng.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.quickeng.ui.screen.HomeScreen
import com.example.quickeng.ui.screen.SplashScreen
import com.example.quickeng.ui.study.SentenceListScreen
import com.example.quickeng.ui.tracker.TrackerScreen
import com.example.quickeng.ui.script.ScriptScreen
import com.example.quickeng.ui.script.toSentenceUi
import com.example.quickeng.ui.study.SentenceUi
import com.example.quickeng.ui.study.TagType

@Composable
fun MainScaffold(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    // 1. 여기서 ViewModel을 생성합니다.
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
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            // 2. 스터디 화면: ViewModel의 데이터를 구독해서 전달
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

            composable(BottomNavItem.Home.route) {
                HomeScreen(onVideoClick = { navController.navigate("script") })
            }

            composable(BottomNavItem.Tracker.route) { TrackerScreen() }

            // 3. 스크립트 화면: 여기서 ViewModel의 addSentences를 호출하도록 연결!
            composable("script") {
                ScriptScreen(
                    onAddClick = { selectedItems ->
                        // ViewModel에 데이터 추가
                        studyVM.addSentences(selectedItems.map { it.toSentenceUi() })
                        // 추가 후 리스트 화면으로 이동
                        navController.navigate(BottomNavItem.Study.route)
                    }
                )
            }
        }
    }
}

