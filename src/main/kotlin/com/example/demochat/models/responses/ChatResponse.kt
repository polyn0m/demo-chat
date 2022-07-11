package com.example.demochat.models.responses

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("last_message")
    val lastMessage: LastMessageResponse?,
    @SerializedName("last_read_message_id")
    val lastReadMessageId: String,
    @SerializedName("unread_count")
    val unreadCount: Int,
    val user: UserResponse
)
