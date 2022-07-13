package com.example.today_s_house_clon.src.main.store

import com.example.today_s_house_clon.src.main.store.models.*
import retrofit2.Call
import retrofit2.http.*


interface StoreRetrofitInterface {

    @GET("/app/store")
    fun getStore(@Header ("X-ACCESS-TOKEN") token: String ): Call<StoreResponse>

    @GET("/app/store/items")
    fun getItemDetail(@Header ("X-ACCESS-TOKEN") token: String, @Query ("id") id: Long, @Query ("user") user: Long ): Call<DetailResponse>

    @POST("/app/store/{userId}/items")
    fun putInBasket(@Header ("X-ACCESS-TOKEN") token: String, @Path ("userId") userId: Long, @Body params: RequestSelectItem, @Query("id") id: Long): Call<SelectItemResponse>

}