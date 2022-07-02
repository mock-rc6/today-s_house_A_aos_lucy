package com.example.today_s_house_clon.src.main.login

import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.src.main.login.models.LoginResponse
import com.example.today_s_house_clon.src.main.login.models.PostLoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginActivityInterface: LoginActivityInterface) {

    fun tryPostLogin(postLoginRequest: PostLoginRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(MembershipRetrofitInterface::class.java)
        loginRetrofitInterface.postLogin(postLoginRequest).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>, response: Response<LoginResponse>
            ) {
                loginActivityInterface.onPostLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginActivityInterface.onPostLoginFailure(t.message ?: "통신오류")
            }

        })
    }
}