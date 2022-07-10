package com.example.demochat

import com.example.demochat.models.WhoResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class ServerApi {
    private val serverUrl = "http://localhost:8080/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val api = Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(client)
        .build()
        .create(Api::class.java)

    interface Api {
        @GET("/api/v1/auth/who/")
        fun getWho(): WhoResponse
    }
}