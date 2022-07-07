package com.example.today_s_house_clon.src.main.myPage

import com.example.today_s_house_clon.src.main.myPage.models.MyPageResponse

interface MyPageInterface {
    fun onGetMyPageSuccess(response: MyPageResponse)
    fun onGetMyPageFailure(message: String)
}