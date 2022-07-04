package com.example.today_s_house_clon.src.main.store.models

data class TodayDeal(
    val itemId: Long,
    val companyName: String,
    val companyId: Long,
    val due: String,
    val subCategory: String,
    val subCategoryId: Long,
    val saleRate: String,
    val price: String,
    val reviewNum: Int,
    val score: Double,
    val itemName: String,
    val hotDealThumbnail: String
)
