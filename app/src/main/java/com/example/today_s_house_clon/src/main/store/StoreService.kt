package com.example.today_s_house_clon.src.main.store

import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.src.main.store.models.StoreResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService(val storeFragmentInterface: StoreFragmentInterface, val token: String) {

    fun tryGetStore(){
        val storeRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        storeRetrofitInterface.getStore(token).enqueue(object :
            Callback<StoreResponse> {
            override fun onResponse(
                call: Call<StoreResponse>, response: Response<StoreResponse>
            ) {
                storeFragmentInterface.onGetStoreSuccess(response.body() as StoreResponse)
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                storeFragmentInterface.onGetStoreFailure(t.message ?: "통신오류")
            }

        })
    }

}