package com.example.quickeng.data.remote

// 서버 요청 바디
data class AnalyzeRequest(
    val videoUrl: String,
    val targetLang: String = "ko"
)

// 서버 응답 전체
data class AnalyzeResponse(
    val videoId: String,
    val title: String,
    val thumbnailUrl: String,
    val scriptItems: List<ScriptItemDto>
)

data class ScriptItemDto(
    val id: String, // 서버 UUID
    val expression: String,
    val meaningKr: String,
    val contextTag: String
)
