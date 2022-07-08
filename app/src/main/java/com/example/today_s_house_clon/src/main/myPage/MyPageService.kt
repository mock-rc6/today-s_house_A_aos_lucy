package com.example.today_s_house_clon.src.main.myPage

import android.util.Log
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.src.main.myPage.models.MyPageResponse
import com.example.today_s_house_clon.src.main.myPage.models.PasswordResponse
import com.example.today_s_house_clon.src.main.myPage.models.RequestPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageService(val myPageInterface: MyPageInterface) {

    fun tryGetMyPage(token: String, userId: Long){
        val myPageRetrofitInterface = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.getMyPage(token, userId).enqueue(object :
            Callback<MyPageResponse> {
            override fun onResponse(
                call: Call<MyPageResponse>, response: Response<MyPageResponse>
            ) {
                myPageInterface.onGetMyPageSuccess(response.body() as MyPageResponse)
            }

            override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                myPageInterface.onGetMyPageFailure(t.message ?: "통신오류")
            }

        })
    }

    fun tryChangePassword(requestPassword: RequestPassword, token: String, userId: Long){
        val myPageRetrofitInterface = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.changePassword(token, requestPassword, userId).enqueue(object :
            Callback<PasswordResponse> {
            override fun onResponse(
                call: Call<PasswordResponse>, response: Response<PasswordResponse>
            ) {
                if (response.isSuccessful) {
                    myPageInterface.onGetChangePasswordSuccess(response.body() as PasswordResponse)
                }else{
                    Log.d("TAG", "onResponse: ${response.message()}", )
                }

            }

            override fun onFailure(call: Call<PasswordResponse>, t: Throwable) {
                myPageInterface.onGetChangePasswordFailure(t.message ?: "통신오류")
            }

        })
    }
}