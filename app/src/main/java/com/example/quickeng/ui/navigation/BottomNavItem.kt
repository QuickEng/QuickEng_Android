package com.example.quickeng.ui.navigation

sealed class BottomNavItem(
    val route: String,
    val label: String
) {
    data object Home : BottomNavItem("home", "홈")
    data object Study : BottomNavItem("study", "학습")
    data object Tracker : BottomNavItem("tracker", "트래커")
}
