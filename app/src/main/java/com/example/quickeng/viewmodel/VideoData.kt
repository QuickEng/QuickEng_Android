package com.example.quickeng.viewmodel

data class VideoData(
    val id: Int,
    val videoUrl: String,
    val learningContent: List<LearningExpression>
)

data class LearningExpression(
    val expression: String,
    val meaningKr: String,
    val contextTag: String
)