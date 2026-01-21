package com.example.quickeng.ui.tracker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatsRow(
    streakDays: Int,
    totalSentences: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        StatCard(
            modifier = Modifier.weight(1f),
            label = "STREAK",
            value = streakDays.toString(),
            unit = "Days"
        )

        StatCard(
            modifier = Modifier.weight(1f),
            label = "SENTENCES",
            value = totalSentences.toString(),
            unit = "Total"
        )
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    unit: String
) {
    Card(
        modifier = modifier.height(124.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(Modifier.fillMaxSize().padding(16.dp)) {

            // 오른쪽 위 아이콘 자리 (불꽃/별 같은거)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(34.dp)
                    .background(Color(0xFFF2F2F2), CircleShape)
            )

            Column(Modifier.align(Alignment.BottomStart)) {
                Text(label, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(value, fontSize = 34.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(Modifier.width(8.dp))
                    Text(unit, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 6.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "StatsRow Preview")
@Composable
private fun StatsRowPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp)
    ) {
        StatsRow(
            streakDays = 5,
            totalSentences = 142
        )
    }
}

