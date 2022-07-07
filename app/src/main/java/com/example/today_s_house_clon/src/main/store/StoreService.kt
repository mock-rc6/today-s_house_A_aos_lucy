package com.example.today_s_house_clon.src.main.store

import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.src.main.store.models.DetailResponse
import com.example.today_s_house_clon.src.main.store.models.StoreResponse
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

    fun tryGetItemDetail( itemId: String, userId: String){
        val storeRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        storeRetrofitInterface.getItemDetail(itemId, userId).enqueue(object :
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

}