package com.example.quickeng.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickeng.R
import com.example.quickeng.ui.theme.Pretendard
import com.example.quickeng.ui.theme.QuickEngTheme

// 더미 데이터 (나중에 실제 데이터로 교체)
data class ShortItem(
    val id: String,
    val title: String
    // val thumbnailUrl: String  // 나중에 추가
)

@Composable
fun HomeScreen(
    onVideoClick: () -> Unit = {}
) {
    // 입력값 상태 유지
    var urlText by rememberSaveable { mutableStateOf("") }
    // 쇼츠 더미 데이터 리스트 (나중에 교체)
    val shorts = remember {
        listOf(
            ShortItem("1", "NYC Slang: What's good?"),
            ShortItem("2", "Ordering Coffee like a pro"),
            ShortItem("3", "Kitchen English: Sizzling"),
            ShortItem("4", "At the Airport: Check-in"),
            ShortItem("5", "Ordering Pizza in Chicago"),
            ShortItem("6", "Hiking Essentials"),
            ShortItem("7", "NYC Slang: What's good?"),
            ShortItem("8", "Ordering Coffee like a pro"),
            ShortItem("9", "Kitchen English: Sizzling"),
            ShortItem("10", "At the Airport: Check-in"),
        )
    }
    // 추천 쇼츠 데이터 빈 경우 상태 처리 확인용 코드
    // val shorts = remember { emptyList<ShortItem>() }


    // 전체 배경/패딩
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        // 1) 상단 로고 영역
        BrandHeader()

        // 2) 입력창 영역
        UrlInputBar(
            value = urlText,
            onValueChange = { urlText = it },
            onClear = { urlText = "" },
            onSubmit = {
                // TODO: 등록 버튼 눌렀을 때 동작
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )


        // 3) 그리드 + 4) 빈 상태
        ShortsSection(
            items = shorts,
            onItemClick = onVideoClick,
            modifier = Modifier.weight(1f)
        )

        Spacer(Modifier.height(16.dp))
    }
}

// 헤더 로고
@Composable
private fun BrandHeader() {
    Image(
        painter = painterResource(id = R.drawable.quickeng_logo),
        contentDescription = "QuickEng Logo",
        modifier = Modifier
            .height(24.dp),
        contentScale = ContentScale.Fit
    )
}

// 입력창 그림자
private fun Modifier.figmaShadow(
    color: Color = Color(0xFF000000),
    alpha: Float = 0.25f,
    offsetX: Dp = 1.dp,
    offsetY: Dp = 1.dp,
    blur: Dp = 4.dp,
    cornerRadius: Dp = 100.dp,
    spread: Dp = 0.dp
): Modifier = this.drawBehind {
    val paint = Paint()
    val frameworkPaint = paint.asFrameworkPaint()

    frameworkPaint.color = color.copy(alpha = alpha).toArgb()
    frameworkPaint.setShadowLayer(
        blur.toPx(),
        offsetX.toPx(),
        offsetY.toPx(),
        color.copy(alpha = alpha).toArgb()
    )

    drawIntoCanvas { canvas ->
        val left = 0f - spread.toPx()
        val top = 0f - spread.toPx()
        val right = size.width + spread.toPx()
        val bottom = size.height + spread.toPx()

        canvas.drawRoundRect(
            left = left,
            top = top,
            right = right,
            bottom = bottom,
            radiusX = cornerRadius.toPx(),
            radiusY = cornerRadius.toPx(),
            paint = paint
        )
    }
}

//입력칸
@Composable
private fun UrlInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val radius = RoundedCornerShape(100.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .figmaShadow(
                color = Color.Black,
                alpha = 0.25f,
                offsetX = 1.dp,
                offsetY = 1.dp,
                blur = 4.dp,
                cornerRadius = 100.dp,
                spread = 0.dp
            )
            .clip(radius)
            .background(Color.White)
            .padding(end = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 입력 영역
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 29.dp, end = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF2E2E2E)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            if (value.isBlank()) {
                Text(
                    text = "URL을 입력해주세요.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF616161)
                )
            }
        }
        // X 버튼 (입력값 있을 때만 표시)
        if (value.isNotBlank()) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(radius)
                    .background(Color(0xFFEDEDED))
                    .clickable { onClear() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Clear",
                    tint = Color(0xFF616161),
                    modifier = Modifier.size(14.dp)
                )
            }
            Spacer(Modifier.width(8.dp))
        }
        // 등록 버튼
        Box(
            modifier = Modifier
                .size(width = 50.dp, height = 38.dp)
                .clip(radius)
                .background(Color(0xFF85C3F6))
                .clickable { onSubmit() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "등록",
                style = TextStyle(
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                ),
                color = Color.White
            )
        }
    }
}

//쇼츠 리스트 영역
@Composable
private fun ShortsSection(
    items: List<ShortItem>,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) {
        EmptyState(modifier)
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(top=28.dp, bottom = 90.dp)
    ) {
        items(items, key = { it.id }) { item ->
            ShortCard(item,onItemClick)
        }
    }
}


@Composable
private fun ShortCard(item: ShortItem,
                      onClick: () -> Unit
                      ) {
    val shape = RoundedCornerShape(18.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(163f / 204f)
            .clip(shape)
            .clickable { onClick() }
    ) {
        // 1) 썸네일 영역
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF616161)) // TODO: 실제 이미지로 교체
        )

        // 2) 하단 그라디언트 오버레이
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.3942f to Color(0x00000000),
                            1.0f to Color(0xFF000000)
                        )
                    )
                )
        )

        // 3) 제목 텍스트
        Text(
            text = item.title,
            style = TextStyle(
                fontFamily = Pretendard,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 12.dp, end = 12.dp, bottom = 15.dp)
        )
    }
}

// 쇼츠 데이터 비었을때 상태 문구 표시
@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "추천 쇼츠가 없어요.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    QuickEngTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        HomeScreen()
    }
}
