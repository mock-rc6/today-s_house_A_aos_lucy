package com.example.today_s_house_clon.src.main.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityJoinBinding
import com.example.today_s_house_clon.src.main.login.models.PostSignUpRequest
import com.example.today_s_house_clon.src.main.login.models.SignUpResponse

class JoinActivity : BaseActivity<ActivityJoinBinding>(ActivityJoinBinding::inflate),
JoinActivityInterface{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnJoin.setOnClickListener {
            val email = binding.etJoinEmail.text.toString()
            val password = binding.etJoinPassword.text.toString()
            val name = binding.etJoinNickname.text.toString()
            val postRequest = PostSignUpRequest(email = email, name = name, password = password)
            showLoadingDialog(this)
            JoinService(this).tryPostSignUp(postRequest)
        }


    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()
        val id = response.result.userId
        val jwt = response.result.jwt

        showCustomToast("$id")
        showCustomToast("$jwt")

    }

    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        Log.d("TAG", "오류 : $message")
        showCustomToast("오류 : $message")
    }
}