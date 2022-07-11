package com.example.demochat.models.views

data class CurrentUserModel(
    val id: String,
    val name: String,
    val surname: String,
    val patronymic: String
) {
    val fullName = "$surname $name $patronymic".trim()
}