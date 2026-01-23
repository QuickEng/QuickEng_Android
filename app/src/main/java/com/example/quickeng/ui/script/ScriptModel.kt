package com.example.quickeng.model

// 1. 공통 데이터 모델 (화면 이동용)
data class VideoScriptData(
    val videoId: String,
    val title: String,
    val scriptLines: List<ScriptItem>
)

// 2. 스크립트 아이템 (스크립트 화면에서 쓸 낱개 문장)
data class ScriptItem(
    val id: Long,
    val tag: String,
    val eng: String,
    val kor: String,
    var isSelected: Boolean = false
)

// 3. 데이터 홀더 (택배 기사)
object ScriptDataHolder {
    var currentData: VideoScriptData? = null
}

//// 4. 로컬 하드코딩 데이터 (홈 화면 클릭용)
//object LocalData {
//    val shortsMap = mapOf(
//        "1" to VideoScriptData(
//            videoId = "sBZfqOcOULI",
//            title = "NYC Slang: What's good?",
//            scriptLines = listOf(
//                ScriptItem(1, "Slang", "What's good?", "별일 없어?"),
//                ScriptItem(2, "Reply", "Chillin'", "그냥 쉬고 있어")
//            )
//        ),
//        "2" to VideoScriptData(
//            videoId = "sBZfqOcOULI",
//            title = "Ordering Coffee",
//            scriptLines = listOf(
//                ScriptItem(1, "Order", "Iced Americano, please.", "아이스 아메리카노 주세요."),
//                ScriptItem(2, "Option", "Extra shot.", "샷 추가요.")
//            )
//        )
//        // 필요한 만큼 더 추가...
//    )
//}