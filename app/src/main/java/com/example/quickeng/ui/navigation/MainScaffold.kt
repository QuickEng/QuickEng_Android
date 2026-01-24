package com.example.quickeng.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quickeng.model.ScriptDataHolder
import com.example.quickeng.ui.analyze.AnalyzeViewModel

@Composable
fun MainScaffold(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val studyVM: com.example.quickeng.viewmodel.StudyVM = androidx.lifecycle.viewmodel.compose.viewModel()

    val vm: AnalyzeViewModel = viewModel()

    val items = listOf(
        BottomNavItem.Study,
        BottomNavItem.Home,
        BottomNavItem.Tracker
    )

    // 현재 route 관찰(바텀바 숨김 조건에 필요)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isBottomBarVisible = currentRoute != "splash" && currentRoute != "script"

    androidx.compose.foundation.layout.Box(
        modifier = modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.fillMaxSize()
        ) {
            // 스플래시 - 시간 지나면 홈으로 이동(뒤로가기 막기)
            composable("splash") {
                SplashScreen(
                    onTimeout = {
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                )
            }

            // 홈 화면
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    vm = vm,
                    onNavigateToScript = { navController.navigate("script") }
                )
            }

            // 저장된 문장 리스트 화면
            composable(BottomNavItem.Study.route) {
                val sentenceItems by studyVM.sentences.collectAsState()
                SentenceListScreen(
                    items = sentenceItems,
                    onDelete = { id -> studyVM.deleteSentence(id) }
                )
            }

            // 트래커 화면
            composable(BottomNavItem.Tracker.route) { TrackerScreen() }

            // 스크립트 화면
            composable("script") {
                ScriptScreen(
                    onAddClick = { selectedItems ->
                        val videoId = ScriptDataHolder.currentData?.videoId ?: "unknown"

                        // 디버그: 최종 저장될 id 확인
                        Log.d(
                            "MainScaffold",
                            "Sentence IDs: " + selectedItems.map { "${videoId}_${it.id}" }
                        )

                        // 선택 문장들을 SentenceUi로 변환해서 StudyVM에 저장
                        studyVM.addSentences(selectedItems.map { it.toSentenceUi(videoId) })

                    }
                )
            }
        }

        // 바텀바 NavHost 위에 오버레이 되도록
        if (isBottomBarVisible) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                BottomBar(
                    items = items,
                    currentRoute = currentRoute,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            // 탭 이동 시 백스택 정리 + 상태 복원
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
    }
}