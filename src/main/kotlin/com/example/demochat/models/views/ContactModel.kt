package com.example.demochat.models.views

data class ContactModel(
    val id: Int,
    val name: String,
    val lastMessageText: String,
    val isGroupChat: Boolean = false,
    val unreadCount: Int = 0
)