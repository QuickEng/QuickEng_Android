package com.example.quickeng

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quickeng.ui.script.ScriptScreen
import com.example.quickeng.ui.script.toSentenceUi
import com.example.quickeng.ui.study.SentenceListScreen
import com.example.quickeng.viewmodel.StudyVM

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    Log.d("AppNavGraph", "AppNavGraph Composable 시작됨")
    val studyVM: StudyVM = viewModel()
    Log.d("AppNavGraph", "ViewModel 인스턴스: ${studyVM.hashCode()}")

    NavHost(
        navController = navController,
        startDestination = Routes.SCRIPT
    ) {
        composable(Routes.SCRIPT) {
            ScriptScreen(
                onAddClick = { selectedItems ->
                    // 1) ViewModel에 저장
                    studyVM.addSentences(selectedItems.map { it.toSentenceUi() })
                    // 2) 화면 이동
                    navController.navigate(Routes.SENTENCE_LIST)
                }
            )
        }

        composable(Routes.SENTENCE_LIST) {
            val items by studyVM.sentences.collectAsState()

            Log.d("SentenceListScreen", "Items from ViewModel: ${items.map { it.id }}")

            SentenceListScreen(
                items = items,
                onDelete = { id -> studyVM.deleteSentence(id) }
            )
        }
    }
}
