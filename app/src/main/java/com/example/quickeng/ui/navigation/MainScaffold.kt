package com.example.quickeng.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.quickeng.ui.screen.HomeScreen
import com.example.quickeng.ui.study.SentenceListScreen
import com.example.quickeng.ui.tracker.TrackerScreen
import com.example.quickeng.ui.script.ScriptScreen
import com.example.quickeng.ui.study.SentenceUi
import com.example.quickeng.ui.study.TagType

@Composable
fun MainScaffold() {
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
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            if (currentRoute != "script") {   // script화면에선 바텀바 안 보이게
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
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
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

