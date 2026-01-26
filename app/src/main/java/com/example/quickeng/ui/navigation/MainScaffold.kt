package com.example.quickeng.ui.navigation

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.quickeng.ui.screen.HomeScreen
import com.example.quickeng.ui.screen.SplashScreen
import com.example.quickeng.ui.study.SentenceListScreen
import com.example.quickeng.ui.tracker.TrackerScreen
import com.example.quickeng.ui.script.ScriptScreen
import com.example.quickeng.ui.script.toSentenceUi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quickeng.model.ScriptDataHolder
import com.example.quickeng.ui.analyze.AnalyzeViewModel
import com.example.quickeng.viewmodel.StudyVM

@Composable
fun MainScaffold(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current

    // StudyVM 생성 (Application 인자 전달)
    val studyVM: StudyVM = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return StudyVM(context.applicationContext as Application) as T
            }
        }
    )

    val vm: AnalyzeViewModel = viewModel()

    val items = listOf(
        BottomNavItem.Study,
        BottomNavItem.Home,
        BottomNavItem.Tracker
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isBottomBarVisible = currentRoute != "splash" && currentRoute != "script"

    Box(modifier = modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("splash") {
                SplashScreen(
                    onTimeout = {
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                )
            }

            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    vm = vm,
                    onNavigateToScript = { navController.navigate("script") }
                )
            }

            composable(BottomNavItem.Study.route) {
                val sentenceItems by studyVM.sentences.collectAsState()
                SentenceListScreen(
                    items = sentenceItems,
                    onDelete = { id -> studyVM.deleteSentence(id) }
                )
            }

            composable(BottomNavItem.Tracker.route) { TrackerScreen() }

            composable("script") {
                ScriptScreen(
                    onAddClick = { selectedItems ->
                        val currentVideoId = ScriptDataHolder.currentData?.videoId ?: "unknown"


                        // Room DB에 저장
                        studyVM.addSentences(selectedItems.map { it.toSentenceUi(currentVideoId) })
                        Log.d("MainScaffold", "Saved with VideoID: $currentVideoId")

                    }
                )
            }
        }

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