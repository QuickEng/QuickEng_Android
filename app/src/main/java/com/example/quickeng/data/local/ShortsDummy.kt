package com.example.quickeng.data.local

data class ShortItem(
    val id: String,
    val title: String,
    val videoId: String,
    val thumbnailUrl: String? = null
)
object ShortsDummy {
    // TODO: 데이터 수정 필요 + 썸네일 이미지 추가
    val shorts = listOf(
        ShortItem("1", "NYC Slang: What's good?", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
        ShortItem("2", "Ordering Coffee like a pro", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
        ShortItem("3", "Kitchen English: Sizzling", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
        ShortItem("4", "At the Airport: Check-in", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
        ShortItem("5", "Ordering Pizza in Chicago", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
        ShortItem("6", "Hiking Essentials", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
        ShortItem("7", "NYC Slang: What's good?", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
        ShortItem("8", "Ordering Coffee like a pro", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
        ShortItem("9", "Kitchen English: Sizzling", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
        ShortItem("10", "At the Airport: Check-in", videoId = "sBZfqOcOULI",
            thumbnailUrl = null),
    )
}
