package com.example.demochat.services

import com.example.demochat.models.responses.ContactsListResponse
import com.example.demochat.models.responses.WhoResponse
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class ChatServer {
    private val serverUrl = "http://localhost:8080/"

    var login = ""
    var password = ""

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .authenticator { _, response ->
            val creds = Credentials.basic(login, password)

            response.request.newBuilder().addHeader("Authorization", creds).build()
        }
        .build()

    val httpApi: Api = Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    interface Api {
        @GET("/api/v1/auth/who/")
        suspend fun getWho(): WhoResponse
        @GET("/api/v1/list/contacts/")
        suspend fun getContactsList(): ContactsListResponse
    }
}