package com.example.today_s_house_clon.src.main.home

import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.src.main.home.models.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val interestFragmentInterface: InterestFragmentInterface, val token: String) {

    fun tryGetHome() {
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getHome(token).enqueue(object : Callback<HomeResponse>{
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                interestFragmentInterface.onGetHomeSuccess(response.body() as HomeResponse)
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                interestFragmentInterface.onGetHomeFailure(t.message ?: "통신 오류")
            }

        })
    }

}