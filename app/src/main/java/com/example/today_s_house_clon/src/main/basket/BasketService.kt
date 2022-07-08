package com.example.today_s_house_clon.src.main.basket

import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.src.main.basket.models.BasketResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketService(val basketInterface: BasketInterface) {

    fun tryGetBasket(token: String,user: Long){
        val basketRetrofitInterface = ApplicationClass.sRetrofit.create(BasketRetrofitInterface::class.java)
        basketRetrofitInterface.getStore(token,user).enqueue(object :
            Callback<BasketResponse> {
            override fun onResponse(
                call: Call<BasketResponse>, response: Response<BasketResponse>
            ) {
                basketInterface.onGetBasketSuccess(response.body() as BasketResponse)
            }

            override fun onFailure(call: Call<BasketResponse>, t: Throwable) {
                basketInterface.onGetBasketFailure(t.message ?: "통신오류")
            }

        })
    }
}