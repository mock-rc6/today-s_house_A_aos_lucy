package com.example.today_s_house_clon.src.main.login.join

import com.example.today_s_house_clon.src.main.login.join.models.SignUpResponse

interface JoinActivityInterface {

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)

}