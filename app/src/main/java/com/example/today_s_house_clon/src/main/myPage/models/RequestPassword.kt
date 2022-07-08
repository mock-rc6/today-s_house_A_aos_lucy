package com.example.today_s_house_clon.src.main.myPage.models

import com.google.gson.annotations.SerializedName

data class RequestPassword(
    @SerializedName("password") val password: String,
)
