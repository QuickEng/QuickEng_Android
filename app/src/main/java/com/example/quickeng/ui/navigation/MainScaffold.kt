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
import com.example.quickeng.ui.study.SentenceUi
import com.example.quickeng.ui.study.TagType

@Composable
fun MainScaffold(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Study,
        BottomNavItem.Home,
        BottomNavItem.Tracker
    )
    // 나중에 viewmodel로 바꿀예정
    val mockSentences = listOf(
        SentenceUi(
            id = "1",
            tag = "NYC Slang",
            tagType = TagType.BLUE,
            en = "“I’m down.” means 'I’m in / I want to do it.'",
            ko = "'I’m down.'은 '나 그거 할래'라는 뜻이에요."
        ),
        SentenceUi(
            id = "2",
            tag = "Ordering Coffee",
            tagType = TagType.PURPLE,
            en = "Can I get it iced, no sugar, and extra shot?",
            ko = "아이스로, 설탕 빼고, 샷 추가해도 될까요?"
        ),
        SentenceUi(
            id = "3",
            tag = "At the Airport",
            tagType = TagType.GREEN,
            en = "Where is the baggage claim?",
            ko = "수하물 찾는 곳이 어디예요?"
        ),
        SentenceUi(
            id = "4",
            tag = "NYC Slang",
            tagType = TagType.BLUE,
            en = "“No worries.” = “It’s okay / don’t stress.”",
            ko = "'No worries.'는 '괜찮아/신경쓰지 마' 느낌이에요."
        )
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
            composable("script") { ScriptScreen(onAddClick = {}) }


        }
    }
}

