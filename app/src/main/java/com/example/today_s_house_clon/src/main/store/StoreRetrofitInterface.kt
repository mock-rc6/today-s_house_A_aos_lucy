package com.example.today_s_house_clon.src.main.store

import com.example.today_s_house_clon.src.main.store.models.DetailResponse
import com.example.today_s_house_clon.src.main.store.models.ResultItemDetail
import com.example.today_s_house_clon.src.main.store.models.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface StoreRetrofitInterface {

    @GET("/app/store")
    fun getStore(@Header ("X-ACCESS-TOKEN") token: String ): Call<StoreResponse>

    @GET("/app/store/items")
    fun getItemDetail(@Header ("X-ACCESS-TOKEN") token: String, @Query ("id") id: Long, @Query ("user") user: Long ): Call<DetailResponse>


}