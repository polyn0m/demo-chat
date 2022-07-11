package com.example.demochat.models.responses

import com.google.gson.annotations.SerializedName

data class LastMessageResponse(
    val id: String,
    @SerializedName("author_id")
    val authorId: Int,
    val message: String,
    val time: String,
    val type: Int
)
