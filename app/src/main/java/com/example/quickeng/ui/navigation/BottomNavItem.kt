package com.example.quickeng.ui.navigation

import com.example.quickeng.R
import androidx.annotation.DrawableRes

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val iconRes: Int
) {
    data object Study : BottomNavItem("study", R.drawable.bottom_book)
    data object Home : BottomNavItem("home", R.drawable.bottom_home)
    data object Tracker : BottomNavItem("tracker", R.drawable.bottom_chart)
}
