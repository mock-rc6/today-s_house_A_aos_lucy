package com.example.today_s_house_clon.src.main.login.models

import com.google.gson.annotations.SerializedName

data class ResultLogin (
    @SerializedName("userId") val userId: Long,
    @SerializedName("jwt") val jwt: String
)