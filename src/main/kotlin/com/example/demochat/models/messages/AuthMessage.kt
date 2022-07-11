package com.example.demochat.models.messages

data class AuthMessage(
    val type: String = "auth",
    val auth: AuthData
)

data class  AuthData(
    val login: String,
    val password: String
)
