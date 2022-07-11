package com.example.demochat.services

import com.example.demochat.models.messages.AuthData
import com.example.demochat.models.messages.AuthMessage
import com.example.demochat.models.messages.BaseMessage
import com.example.demochat.models.messages.ChatMessage
import com.example.demochat.models.responses.ContactsListResponse
import com.example.demochat.models.responses.WhoResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class ChatServer : WebSocketListener() {
    private val serverUrl = "http://localhost:8080/"
    private val wsUrl = "ws://localhost:8080/ws/"

    private var webSocket: WebSocket? = null
    private val gsonWsConverter = Gson()

    var login = ""
    var password = ""

    private val wsClient = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()

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

    val wsApi = MutableSharedFlow<BaseMessage>()

    interface Api {
        @GET("/api/v1/auth/who/")
        suspend fun getWho(): WhoResponse
        @GET("/api/v1/list/contacts/")
        suspend fun getContactsList(): ContactsListResponse
    }

    fun connectToChat() {
        val request = Request.Builder()
            .url(wsUrl)
            .build()

        webSocket = wsClient.newWebSocket(request, this)
    }

    fun disconnect() {
        webSocket?.close(1000, "Disconnected")
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        sendAuthMessage(AuthMessage(auth = AuthData(login, password)))
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        try {
            val model = gsonWsConverter.fromJson(text, BaseMessage::class.java)

            if (model.type in listOf("chat", "groupchat")) {
                // TODO: emit to flow
            }
        } catch (e: java.lang.Exception) {

        }
    }

    private fun sendAuthMessage(message: AuthMessage) {
        try {
            val toSend = gsonWsConverter.toJson(message)

            webSocket?.send(toSend)
        } catch (e: java.lang.Exception) {

        }
    }
}