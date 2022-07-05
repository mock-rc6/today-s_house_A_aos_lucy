package com.example.today_s_house_clon.src.main.home

import com.example.today_s_house_clon.src.main.home.models.HomeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeRetrofitInterface {

    @GET("/app")
    fun getHome( @Header("X-ACCESS-TOKEN") token: String ): Call<HomeResponse>

}