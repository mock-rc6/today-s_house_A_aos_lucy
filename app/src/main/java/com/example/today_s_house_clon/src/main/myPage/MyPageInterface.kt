package com.example.today_s_house_clon.src.main.myPage

import com.example.today_s_house_clon.src.main.myPage.models.MyPageResponse
import com.example.today_s_house_clon.src.main.myPage.models.PasswordResponse

interface MyPageInterface {
    fun onGetMyPageSuccess(response: MyPageResponse)
    fun onGetMyPageFailure(message: String)

    fun onGetChangePasswordSuccess(response: PasswordResponse)
    fun onGetChangePasswordFailure(message: String)
}