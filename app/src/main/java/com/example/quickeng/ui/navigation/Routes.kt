package com.example.quickeng.ui.navigation

object Routes {
    const val HOME = "home"
    const val STUDY = "study"
    const val TRACKER = "tracker"

    // 서버 분석 결과로 진입 (ViewModel에 저장된 성공 데이터 사용)
    const val SCRIPT_FROM_SERVER = "script/fromServer"

    // 로컬 더미로 진입 (videoId로 찾기)
    const val SCRIPT_FROM_LOCAL = "script/fromLocal/{videoId}"
    const val ARG_VIDEO_ID = "videoId"
}
