package com.example.quickeng.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomBar(
    items: List<BottomNavItem>,
    currentRoute: String?,
    onItemClick: (BottomNavItem) -> Unit
) {
    // 바텀바 바깥 여백
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp, start = 86.dp, end = 86.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .height(72.dp)
                .width(222.dp)
                .shadow(8.dp, RoundedCornerShape(100.dp))
                .background(Color(0xE53E3E3E), RoundedCornerShape(100.dp))
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route

                Box(
                    modifier = Modifier
                        .shadow(elevation = 8.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
                        .width(64.dp)
                        .height(64.dp)
                        .background(color = if(selected) Color(0xFF000000) else Color.Transparent, shape = RoundedCornerShape(size = 100.dp))
                        .clickable { onItemClick(item) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.route,
                        tint = Color.White,
                        modifier = Modifier.size(21.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2E2E2E)
@Composable
private fun BottomBarPreview_HomeSelected() {
    BottomBar(
        items = listOf(
            BottomNavItem.Study,
            BottomNavItem.Home,
            BottomNavItem.Tracker
        ),
        currentRoute = BottomNavItem.Home.route,
        onItemClick = {}
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF2E2E2E)
@Composable
private fun BottomBarPreview_StudySelected() {
    BottomBar(
        items = listOf(
            BottomNavItem.Study,
            BottomNavItem.Home,
            BottomNavItem.Tracker
        ),
        currentRoute = BottomNavItem.Study.route,
        onItemClick = {}
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF2E2E2E)
@Composable
private fun BottomBarPreview_TrackerSelected() {
    BottomBar(
        items = listOf(
            BottomNavItem.Study,
            BottomNavItem.Home,
            BottomNavItem.Tracker
        ),
        currentRoute = BottomNavItem.Tracker.route,
        onItemClick = {}
    )
}