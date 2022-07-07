package com.example.today_s_house_clon.src.main.myPage.models

import com.google.gson.annotations.SerializedName

data class ResultMyPage(
    @SerializedName("name") val name: String,
    @SerializedName("profilePic") val profilePic: String,
    @SerializedName("follows") val follows: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("likes") val likes: Int,
    @SerializedName("scraps") val scraps: Int,
    @SerializedName("orderHistory") val orderHistory: Int,
    @SerializedName("coupons") val coupons: Int,
    @SerializedName("points") val points: Int,
    @SerializedName("inquiry") val inquiry: Int,
    @SerializedName("myReviews") val myReviews: Int
)
