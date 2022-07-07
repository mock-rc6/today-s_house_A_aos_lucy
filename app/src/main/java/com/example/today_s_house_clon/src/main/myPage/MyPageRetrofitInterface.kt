package com.example.today_s_house_clon.src.main.myPage

import com.example.today_s_house_clon.src.main.myPage.models.MyPageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MyPageRetrofitInterface {
    @GET("/app/{userId}")
    fun getMyPage(@Header ("X-ACCESS-TOKEN") token: String, @Path("userId") userId: Long): Call<MyPageResponse>
}