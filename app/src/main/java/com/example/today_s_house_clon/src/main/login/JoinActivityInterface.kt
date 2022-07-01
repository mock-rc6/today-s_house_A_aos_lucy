package com.example.today_s_house_clon.src.main.login

import com.example.today_s_house_clon.src.main.login.models.SignUpResponse

interface JoinActivityInterface {

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)

}