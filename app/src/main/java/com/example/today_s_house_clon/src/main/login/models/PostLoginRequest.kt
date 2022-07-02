package com.example.today_s_house_clon.src.main.login.models

import com.google.gson.annotations.SerializedName

data class PostLoginRequest (
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)