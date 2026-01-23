package com.example.quickeng.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface QuickEngApi {
    @POST("v1/video/analyze")
    suspend fun analyze(@Body body: AnalyzeRequest): AnalyzeResponse
}
