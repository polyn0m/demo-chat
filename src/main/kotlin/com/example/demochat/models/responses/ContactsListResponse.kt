package com.example.demochat.models.responses

import com.google.gson.annotations.SerializedName

data class ContactsListResponse(
    val chats: List<ChatResponse>,
    @SerializedName("groupchats")
    val groupChats: List<GroupChatResponse>,
)
