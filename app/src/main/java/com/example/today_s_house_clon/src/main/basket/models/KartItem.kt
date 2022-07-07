package com.example.today_s_house_clon.src.main.basket.models

data class KartItem(
    val kartId: Long,
    val imgUrl: String,
    val optionName: String,
    val optionId: Long,
    val itemNum:String,
    val price: String,
    val delivery: String
)
