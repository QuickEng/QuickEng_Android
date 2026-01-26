package com.example.quickeng.data

import com.example.quickeng.data.remote.AnalyzeRequest
import com.example.quickeng.data.remote.AnalyzeResponse
import com.example.quickeng.data.remote.RetrofitProvider

// ViewModel/UseCase에서 "네트워크 호출"을 직접 하지 않도록 중간에서 담당하는 계층
class VideoRepository {
    // 유튜브 영상 URL을 서버로 보내 분석 결과를 받아옴
    suspend fun analyze(videoUrl: String, targetLang: String = "ko"): AnalyzeResponse {
        return RetrofitProvider.api.analyze(
            AnalyzeRequest(videoUrl = videoUrl, targetLang = targetLang)
        )
    }
}
