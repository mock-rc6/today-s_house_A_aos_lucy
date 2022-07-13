package com.example.today_s_house_clon.src.main.store.models

import com.google.gson.annotations.SerializedName

data class ResultSelectItem(
    @SerializedName("kartId") val kartId: Long,
    @SerializedName("message") val message: String
)
