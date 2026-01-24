package com.example.quickeng.data.local

import androidx.annotation.DrawableRes
import com.example.quickeng.R

data class ShortItem(
    val id: String,
    val title: String,
    val videoId: String,
    @DrawableRes val thumbnailRes: Int? = null
)
object ShortsDummy {
    // TODO: 데이터 수정 필요 + 썸네일 이미지 추가
    val shorts = listOf(
        ShortItem("1", "NYC Slang: What's good?", videoId = "sBZfqOcOULI",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("2", "Ordering Coffee like a pro", videoId = "dQw4w9WgXcQ",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("3", "Kitchen English: Sizzling", videoId = "sBZfqOcOULI",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("4", "At the Airport: Check-in", videoId = "sBZfqOcOULI",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("5", "Ordering Pizza in Chicago", videoId = "sBZfqOcOULI",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("6", "Hiking Essentials", videoId = "sBZfqOcOULI",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("7", "NYC Slang: What's good?", videoId = "sBZfqOcOULI",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("8", "Ordering Coffee like a pro", videoId = "sBZfqOcOULI",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("9", "Kitchen English: Sizzling", videoId = "sBZfqOcOULI",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("10", "At the Airport: Check-in", videoId = "sBZfqOcOULI",
            thumbnailRes = R.drawable.thumb1),
    )
}
