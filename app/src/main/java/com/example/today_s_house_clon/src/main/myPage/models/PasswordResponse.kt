package com.example.today_s_house_clon.src.main.myPage.models

data class PasswordResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)