package com.example.today_s_house_clon.src.main.basket.models

import com.google.gson.annotations.SerializedName

data class ResultBasket(
    @SerializedName("kartItemList") val kartItemList: List<KartItem>,
    @SerializedName("itemNum") val itemNum: String,
    @SerializedName("saledPrice") val saledPrice: String,
    @SerializedName("price") val price: String,
    @SerializedName("discountPrice") val discountPrice: String,
    @SerializedName("delivery") val delivery: String,
)
