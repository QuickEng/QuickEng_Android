package com.example.quickeng.data.local

import com.example.quickeng.model.ScriptItem

object LocalScriptsDummy {
    val scriptsByShortId: Map<String, List<ScriptItem>> = mapOf(
        "1" to listOf(
            ScriptItem(1, "Slang", "What's good?", "별일 없어?"),
            ScriptItem(2, "Reply", "Chillin'", "그냥 쉬고 있어"),
            ScriptItem(3, "Reply", "Chillin'", "그냥 쉬고 있어")

        ),
        "2" to listOf(
            ScriptItem(1, "Order", "Iced Americano, please.", "아이스 아메리카노 주세요."),
            ScriptItem(2, "Option", "Extra shot.", "샷 추가요.")
        ),
        // TODO: 더미 데이터 추가
    )
}
