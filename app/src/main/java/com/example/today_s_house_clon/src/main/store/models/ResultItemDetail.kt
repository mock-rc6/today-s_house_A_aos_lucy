package com.example.today_s_house_clon.src.main.store.models

import com.google.gson.annotations.SerializedName

data class ResultItemDetail(
    @SerializedName("itemName") val itemName: String,
    @SerializedName("imgList") val imgList: List<String>,
    @SerializedName("companyId") val companyId: Long,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("score") val score: Double,
    @SerializedName("reviewCnt") val reviewCnt: Int,
    @SerializedName("saleRate") val saleRate: String,
    @SerializedName("price") val price: String,
    @SerializedName("scrapCnt") val scrapCnt: Int,
    @SerializedName("reviewList") val reviewList: List<ReviewList>,
    @SerializedName("five") val five: Int,
    @SerializedName("four") val four: Int,
    @SerializedName("three") val three: Int,
    @SerializedName("two") val two: Int,
    @SerializedName("one") val one: Int,
    @SerializedName("inquiry") val inquiry: Int,
    @SerializedName("scrap") val scrap: Boolean

)
