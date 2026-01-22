package com.example.quickeng.ui.tracker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quickeng.ui.theme.*

@Composable
fun WeeklyChartCard(
    weekly: List<Int>,
    highlightIndex: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(238.dp),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Grey3),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp,)
        ) {
            Text(
                text = "WEEKLY LEARNING STATUS",
                style = QuickEngTypography.bodyLarge,
                color = Black
            )

            Spacer(Modifier.height(51.dp))

            WeeklyBarsWithAxis(
                values = weekly,
                highlightIndex = highlightIndex
            )
        }
    }
}

@Composable
private fun WeeklyBarsWithAxis(
    values: List<Int>,
    highlightIndex: Int
) {
    val days = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
    val safeValues = if (values.size >= 7) values.take(7) else values + List(7 - values.size) { 0 }

    val yMax = 5
    val avg = safeValues.average().toFloat()

    val barAreaHeight = 100.dp
    val barWidth = 34.dp

    val barShape = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 8.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        // Y축 숫자 (5~0)
        Column(
            modifier = Modifier
                .height(barAreaHeight)
                .padding(end = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            for (i in yMax downTo 0) {
                Text(
                    text = i.toString(),
                    style = QuickEngTypography.bodySmall,
                    color = Grey1
                )
            }
        }

        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(barAreaHeight)
            ) {
                // 막대들
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    safeValues.forEachIndexed { index, v ->
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            val clamped = v.coerceIn(0, yMax)
                            val barHeight = (clamped.toFloat() / yMax.toFloat()) * barAreaHeight.value

                            Box(
                                modifier = Modifier
                                    .width(barWidth)
                                    .height(barHeight.dp)
                                    .background(
                                        color = if (index == highlightIndex) PrimaryColor else Grey3,
                                        shape = barShape
                                    )
                            )
                        }
                    }
                }


                // 평균 점선
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val ratio = (avg / yMax.toFloat()).coerceIn(0f, 1f)
                    val y = size.height * (1f - ratio)

                    drawLine(
                        color = PrimaryColor,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                }
            }

            Spacer(Modifier.height(6.dp))

            // 요일
            Row(modifier = Modifier.fillMaxWidth()) {
                days.forEachIndexed { index, day ->
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day,
                            style = QuickEngTypography.bodySmall,
                            color = if (index == highlightIndex) PrimaryColor else Grey1
                        )
                    }
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
            .background(White)
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
            .background(White)
            .padding(20.dp)
    ) {
        WeeklyChartCard(
            weekly = listOf(0, 0, 0, 0, 0, 0, 0),
            highlightIndex = 4
        )
    }
}
