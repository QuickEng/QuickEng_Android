package com.example.quickeng.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
// Retrofit(네트워크 통신) 설정을 한 곳에서 관리
object RetrofitProvider {

    // 서버 기본 주소
    private const val BASE_URL = "https://unmaledictive-lourdes-premonetary.ngrok-free.dev/"

    // 요청/응답 로그 출력용 (개발 중 디버깅 편의를 위해)
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // 모든 요청에 공통 헤더를 자동으로 붙이는 인터셉터
    // ngrok 무료 플랜에서 뜨는 브라우저 경고 페이지를 우회하기 위한 헤더
    private val headerInterceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()

            // 모든 요청에 헤더 추가
            val request = original.newBuilder()
                .header("ngrok-skip-browser-warning", "69420")
                .method(original.method, original.body)
                .build()

            return chain.proceed(request)
        }
    }

    // 인터셉터(로그/헤더) 등록
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(headerInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    // 실제 API 호출에 사용할 QuickEngApi 인스턴스
    // lazy: 최초 접근 시 1번만 생성해서 재사용
    val api: QuickEngApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()) // JSON <-> 객체 변환
            .build()
            .create(QuickEngApi::class.java)
    }
}