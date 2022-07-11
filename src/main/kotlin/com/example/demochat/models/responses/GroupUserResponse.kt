package com.example.demochat.models.responses

import com.google.gson.annotations.SerializedName

data class GroupUserResponse(
    @SerializedName("permission_level")
    val permissionLevel: Int,
    val user: UserResponse
)
