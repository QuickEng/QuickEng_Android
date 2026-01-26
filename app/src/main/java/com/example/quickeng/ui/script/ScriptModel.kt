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

// 3. 데이터 홀더
object ScriptDataHolder {
    var currentData: VideoScriptData? = null
}
