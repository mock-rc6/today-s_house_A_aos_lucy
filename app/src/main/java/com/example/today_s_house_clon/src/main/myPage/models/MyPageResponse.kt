package com.example.today_s_house_clon.src.main.myPage.models

import com.example.today_s_house_clon.config.BaseResponse
import com.example.today_s_house_clon.src.main.store.models.ResultStore
import com.google.gson.annotations.SerializedName

class MyPageResponse (
    // 베이스 리스폰스를 상속 받았으므로, 아래 내용은 포함이 되었습니다.
    // @SerializedName("isSuccess") val isSuccess: Boolean,
    // @SerializedName("code") val code: Int,
    // @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultMyPage
) : BaseResponse()