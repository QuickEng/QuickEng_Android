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
        ShortItem("1", "Does vitamin C actually help prevent the flu?", videoId = "ejfyeS5igJU",
            thumbnailRes = R.drawable.thumb1),
        ShortItem("2", "So our bodies aren’t full of microplastics after all?", videoId = "JGwkiXprw7I",
            thumbnailRes = R.drawable.thumb2),
        ShortItem("3", "The number one song in America…is made by AI?", videoId = "gGHyObTRU7c",
            thumbnailRes = R.drawable.thumb3),
        ShortItem("4", "This town wants more nuclear energy", videoId = "E257saHzVRM",
            thumbnailRes = R.drawable.thumb4),
        ShortItem("5", "Inside the $300 million project to save the Great Barrier Reef ", videoId = "MzcCkLGBKaE",
            thumbnailRes = R.drawable.thumb5),
        ShortItem("6", "We picked 23 Innovation Award winners", videoId = "Lkq06snGce8",
            thumbnailRes = R.drawable.thumb6),
        ShortItem("7", "Robot dogs check human work at EV factory", videoId = "xex7BlUk_w0",
            thumbnailRes = R.drawable.thumb7),
        ShortItem("8", "Michelin-starred chef opened a restaurant in an EV factory", videoId = "j4luSy9Ysyk",
            thumbnailRes = R.drawable.thumb8),
        ShortItem("9", "Digital Signature: The Future of Quantum Computing", videoId = "QhYMyEIcydQ",
            thumbnailRes = R.drawable.thumb9),
        ShortItem("10", "Quantum Computing Insights", videoId = "IYuLgsukXhY",
            thumbnailRes = R.drawable.thumb10),
    )
}
