package com.example.quickeng

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quickeng.ui.screen.HomeScreen
import com.example.quickeng.ui.script.ScriptScreen
import com.example.quickeng.ui.script.toSentenceUi
import com.example.quickeng.ui.study.SentenceListScreen
import com.example.quickeng.viewmodel.StudyVM

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val studyVM: StudyVM = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME // 시작을 홈으로 하는 것이 흐름상 자연스럽습니다
    ) {
        // 홈 화면 (이전에 작성하신 HomeScreen이 여기에 들어갑니다)
        composable(Routes.HOME) {
            HomeScreen(onVideoClick = { id ->
                navController.navigate("script/$id")
            })
        }

        // 스크립트 화면 (videoId를 필수로 받는 경로로 통일)
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
                    navController.navigate(Routes.SENTENCE_LIST)
                }
            )
        }

        // 문장 리스트 화면
        composable(Routes.SENTENCE_LIST) {
            val items by studyVM.sentences.collectAsState()
            SentenceListScreen(
                items = items,
                onDelete = { id -> studyVM.deleteSentence(id) }
            )
        }
    }
}
