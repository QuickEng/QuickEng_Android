package com.example.quickeng.ui.tracker


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quickeng.ui.theme.QuickEngTypography

@Composable
fun TrackerScreen() {
    val streakDays = 5
    val totalSentences = 142
    val weekly = listOf(3, 1, 5, 3, 3, 1, 4)
    val highlightIndex = 4

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { TrackerTitle(title = "Tracker") }
        item { Spacer(modifier = Modifier.height(36.dp)) }

        item { BannerCard(text = "지금까지의 학습을 확인해보세요.") }

        item {
            StatsRow(
                streakDays = streakDays,
                totalSentences = totalSentences
            )
        }

        item {
            WeeklyChartCard(
                weekly = weekly,
                highlightIndex = highlightIndex
            )
        }
    }
}

@Composable
private fun TrackerTitle(title: String) {
    Text(
        text = title,
        style = QuickEngTypography.headlineLarge,
        color = Color.Black
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "Tracker Screen Preview")
@Composable
private fun TrackerScreenPreview() {
    TrackerScreen()
}
