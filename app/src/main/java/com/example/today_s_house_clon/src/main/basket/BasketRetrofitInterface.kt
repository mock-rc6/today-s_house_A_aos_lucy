package com.example.today_s_house_clon.src.main.basket

import com.example.today_s_house_clon.src.main.basket.models.BasketResponse
import com.example.today_s_house_clon.src.main.store.models.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface BasketRetrofitInterface {

    @GET("/users/karts/:userId")
    fun getStore(@Header("X-ACCESS-TOKEN") token: String ): Call<BasketResponse>

}