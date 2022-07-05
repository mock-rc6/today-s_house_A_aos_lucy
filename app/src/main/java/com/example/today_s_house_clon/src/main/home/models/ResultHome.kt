package com.example.today_s_house_clon.src.main.home.models

import com.google.gson.annotations.SerializedName

data class ResultHome (
    @SerializedName("getEventInfos") val eventInfo: List<EventInfo>,
    @SerializedName("categoryList") val categoryList: List<Long>,
    @SerializedName("getMainHouseInfos") val mainHouseInfo: List<MainHouseInfo>
)