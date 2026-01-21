package com.example.quickeng.ui.tracker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 주간 학습 카드 (막대 + 평균 점선)
 */
@Composable
fun WeeklyChartCard(
    weekly: List<Int>,
    highlightIndex: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(238.dp),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "WEEKLY LEARNING STATUS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(Modifier.height(16.dp))

            SimpleWeeklyBars(
                values = weekly,
                highlightIndex = highlightIndex
            )
        }
    }
}

/**
 * 막대 그래프 + 평균선(점선)
 */
@Composable
fun SimpleWeeklyBars(
    values: List<Int>,
    highlightIndex: Int
) {
    val days = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

    val safeValues = if (values.size >= 7) values.take(7) else values + List(7 - values.size) { 0 }

    val max = (safeValues.maxOrNull() ?: 1).coerceAtLeast(1)
    val avg = if (safeValues.isNotEmpty()) safeValues.average().toFloat() else 0f

    // 막대가 실제로 커질 수 있는 최대 높이(너가 barHeight를 120f로 계산했으니 여기도 120dp)
    val barMaxHeightDp = 120.dp

    // 막대 영역(막대 + 평균선) 높이
    val chartBoxHeight = 140.dp

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(chartBoxHeight)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                // 1) 막대 먼저
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    safeValues.forEachIndexed { index, v ->
                        val barHeight = (v.toFloat() / max.toFloat()) * 120f

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .width(28.dp)
                                    .height(barHeight.dp)
                                    .background(
                                        color = if (index == highlightIndex) Color(0xFF8EC7FF) else Color(
                                            0xFFE7E7E7
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = days[index],
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (index == highlightIndex) Color(0xFF8EC7FF) else Color.Gray
                            )
                        }
                    }
                }

                // 2) 점선을 나중에 그리면 위로 올라옴
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val ratio = (avg / max.toFloat()).coerceIn(0f, 1f)
                    val barAreaHeightPx = barMaxHeightDp.toPx()
                    val barTopY = size.height - barAreaHeightPx
                    val y = barTopY + (barAreaHeightPx * (1f - ratio))

                    drawLine(
                        color = Color(0xFF6FAEFF),
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "WeeklyChartCard - With Avg Line")
@Composable
private fun WeeklyChartCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp)
    ) {
        WeeklyChartCard(
            weekly = listOf(3, 1, 5, 3, 3, 1, 4),
            highlightIndex = 4
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "WeeklyChartCard - Empty")
@Composable
private fun WeeklyChartCardPreview_Empty() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp)
    ) {
        WeeklyChartCard(
            weekly = listOf(0, 0, 0, 0, 0, 0, 0),
            highlightIndex = 4
        )
    }
}
