package com.example.today_s_house_clon.src.main.store.models

import com.google.gson.annotations.SerializedName

data class RequestSelectItem(
    @SerializedName("optionId") val optionId: Long,
    @SerializedName("number") val number: Int
)
