package com.example.today_s_house_clon.src.main.myPage

import com.example.today_s_house_clon.src.main.myPage.models.MyPageResponse
import com.example.today_s_house_clon.src.main.myPage.models.PasswordResponse
import com.example.today_s_house_clon.src.main.myPage.models.RequestPassword
import retrofit2.Call
import retrofit2.http.*

interface MyPageRetrofitInterface {
    @GET("/app/{userId}")
    fun getMyPage(@Header ("X-ACCESS-TOKEN") token: String, @Path("userId") userId: Long): Call<MyPageResponse>

    @PATCH("/users/{userId}")
    fun changePassword(@Header ("X-ACCESS-TOKEN") token: String, @Body params: RequestPassword, @Path("userId") userId: Long): Call<PasswordResponse>

}