package com.example.today_s_house_clon.src.main.login

import com.example.today_s_house_clon.src.main.login.join.models.PostSignUpRequest
import com.example.today_s_house_clon.src.main.login.join.models.SignUpResponse
import com.example.today_s_house_clon.src.main.login.models.LoginResponse
import com.example.today_s_house_clon.src.main.login.models.PostLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MembershipRetrofitInterface {
    @POST("/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>

    @POST("/users/log-in")
    fun postLogin(@Body params: PostLoginRequest): Call<LoginResponse>
}