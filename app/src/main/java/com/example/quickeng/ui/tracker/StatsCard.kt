package com.example.quickeng.ui.tracker

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quickeng.R
import com.example.quickeng.ui.theme.*
import com.example.quickeng.ui.theme.QuickEngTypography

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
            unit = "Days",
            rightTopIconRes = R.drawable.fire_ic
        )

        StatCard(
            modifier = Modifier.weight(1f),
            label = "SENTENCES",
            value = totalSentences.toString(),
            unit = "Total",
            rightTopIconRes = R.drawable.star_ic
        )
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    unit: String,
    rightTopIconRes: Int
) {
    Card(
        modifier = modifier.height(124.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Grey3),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {
            TopIcon(
                modifier = Modifier.align(Alignment.TopEnd),
                iconRes = rightTopIconRes
            )

            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = label,
                    style = QuickEngTypography.bodyLarge,
                    color = Black
                )
                Spacer(Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = value,
                        style = QuickEngTypography.displayLarge,
                        color = Black
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = unit,
                        style = QuickEngTypography.bodyLarge,
                        color = Grey2,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
            }
        }
    }
}


@Composable
private fun TopIcon(
    modifier: Modifier = Modifier,
    iconRes: Int
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Grey3),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "StatsRow Preview")
@Composable
private fun StatsRowPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(20.dp)
    ) {
        StatsRow(
            streakDays = 5,
            totalSentences = 142
        )
    }
}
