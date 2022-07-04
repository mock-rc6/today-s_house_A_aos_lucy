package com.example.today_s_house_clon.src.main.store

import com.example.today_s_house_clon.src.main.store.models.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface StoreRetrofitInterface {

    @GET("/app/store")
    fun getStore( @Header ("X-ACCESS-TOKEN") token: String ): Call<StoreResponse>
}