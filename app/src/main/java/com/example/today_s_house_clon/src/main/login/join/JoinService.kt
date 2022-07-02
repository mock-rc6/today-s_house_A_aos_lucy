package com.example.today_s_house_clon.src.main.login.join

import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.src.main.login.MembershipRetrofitInterface
import com.example.today_s_house_clon.src.main.login.join.models.PostSignUpRequest
import com.example.today_s_house_clon.src.main.login.join.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinService(val joinActivityInterface: JoinActivityInterface) {

    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
        val JoinRetrofitInterface = ApplicationClass.sRetrofit.create(MembershipRetrofitInterface::class.java)
        JoinRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object :
            Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                joinActivityInterface.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                joinActivityInterface.onPostSignUpFailure(t.message ?: "통신오류")
            }
        })
    }
}