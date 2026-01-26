package com.example.quickeng.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

//영상 URL을 서버에 전달해 스크립트(학습 문장) 분석 결과를 받는 API
interface QuickEngApi {
    @POST("v1/video/analyze")
    suspend fun analyze(@Body body: AnalyzeRequest): AnalyzeResponse
}
