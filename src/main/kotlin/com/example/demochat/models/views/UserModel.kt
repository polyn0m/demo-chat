package com.example.demochat.models.views

data class UserModel(
    val id: Int,
    val name: String,
    val surname: String,
    val patronymic: String
) {
    val fullName = "$surname $name $patronymic".trim()
}