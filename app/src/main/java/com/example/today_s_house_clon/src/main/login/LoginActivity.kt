package com.example.today_s_house_clon.src.main.login

import android.content.Intent
import android.os.Bundle
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 이메일 비밀번호 입력 후 로그인시 입력 값 전달
        binding.btnLogin.setOnClickListener {

            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()


            val login = Intent(this, JoinMembershipActivity::class.java)
            login.putExtra("email", "${binding.etLoginEmail}")
            login.putExtra("password", "${binding.etLoginPassword}")
            setResult(100, )
            finish()

        }


    }
}