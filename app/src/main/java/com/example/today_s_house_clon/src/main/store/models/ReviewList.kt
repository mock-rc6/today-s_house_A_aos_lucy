package com.example.today_s_house_clon.src.main.store.models

data class ReviewList(
    val userId: Long,
    val userName: String,
    val profilePic: String,
    val score: Double,
    val createdAt: String,
    val buyAt: String,
    val description: String,
    val imgList: List<String>
)
