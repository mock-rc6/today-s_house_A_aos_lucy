package com.example.today_s_house_clon.src.main.login

import com.example.today_s_house_clon.src.main.login.models.PostSignUpRequest
import com.example.today_s_house_clon.src.main.login.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MembershipRetrofitInterface {
    @POST("/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>

}