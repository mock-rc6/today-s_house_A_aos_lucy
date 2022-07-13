package com.example.today_s_house_clon.src.main.store

import android.util.Log
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.src.main.store.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService(val storeInterface: StoreInterface) {

    fun tryGetStore(token: String){
        val storeRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        storeRetrofitInterface.getStore(token).enqueue(object :
            Callback<StoreResponse> {
            override fun onResponse(
                call: Call<StoreResponse>, response: Response<StoreResponse>
            ) {
                storeInterface.onGetStoreSuccess(response.body() as StoreResponse)
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                storeInterface.onGetStoreFailure(t.message ?: "통신오류")
            }

        })
    }

    fun tryGetItemDetail( token: String, itemId: Long, userId: Long){
        val storeRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        storeRetrofitInterface.getItemDetail(token, itemId, userId).enqueue(object :
            Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>, response: Response<DetailResponse>
            ) {
                storeInterface.onGetItemDetailSuccess(response.body() as DetailResponse)
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                storeInterface.onGetItemDetailFailure(t.message ?: "통신오류")
            }

        })
    }

    fun tryPutInBasket(token: String, userId: Long, requestSelectItem: RequestSelectItem, itemId: Long){
        val putInBasketRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        putInBasketRetrofitInterface.putInBasket(token, userId , requestSelectItem, itemId).enqueue(object:
        Callback<SelectItemResponse>{
            override fun onResponse(
                call: Call<SelectItemResponse>,
                response: Response<SelectItemResponse>
            ) {
                storeInterface.onPutInBasketSuccess(response.body() as SelectItemResponse)
            }

            override fun onFailure(call: Call<SelectItemResponse>, t: Throwable) {
                storeInterface.onPutInBasketFailure(t.message ?: "통신오류")
            }

        })
    }

}