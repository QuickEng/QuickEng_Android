package com.example.quickeng.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
object RetrofitProvider {

    private const val BASE_URL = "https://unmaledictive-lourdes-premonetary.ngrok-free.dev/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

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

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(headerInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    val api: QuickEngApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuickEngApi::class.java)
    }
}