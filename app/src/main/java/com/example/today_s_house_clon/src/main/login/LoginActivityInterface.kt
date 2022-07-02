package com.example.today_s_house_clon.src.main.login

import com.example.today_s_house_clon.src.main.login.models.LoginResponse

interface LoginActivityInterface {

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)
}