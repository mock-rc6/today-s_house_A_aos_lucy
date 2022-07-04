package com.example.today_s_house_clon.src.main.store.models

import com.google.gson.annotations.SerializedName

data class ResultStore (
    @SerializedName("eventImgs") val eventImgs: List<EventImg>,
    @SerializedName("categoryIdList") val categoryIdList: List<Long>,
    @SerializedName("todaysDealList") val todaysDealList: List<TodayDeal>
)