package com.example.quickeng.data

import com.example.quickeng.data.remote.AnalyzeRequest
import com.example.quickeng.data.remote.AnalyzeResponse
import com.example.quickeng.data.remote.RetrofitProvider

class VideoRepository {

    suspend fun analyze(videoUrl: String, targetLang: String = "ko"): AnalyzeResponse {
        return RetrofitProvider.api.analyze(
            AnalyzeRequest(videoUrl = videoUrl, targetLang = targetLang)
        )
    }
}
