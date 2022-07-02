package com.example.today_s_house_clon.src.main.login

import android.content.Intent
import android.os.Bundle
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityJoinMembershipBinding
import com.example.today_s_house_clon.src.main.MainActivity
import com.example.today_s_house_clon.src.main.login.join.JoinActivity

class JoinMembershipActivity :BaseActivity<ActivityJoinMembershipBinding>(ActivityJoinMembershipBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // 카카오톡 로그인 버튼
        binding.btnKakaoLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 이메일 로그인 버튼
        binding.tvLoginToEmail.setOnClickListener {
            val emailLogin = Intent(this, LoginActivity::class.java)
            startActivityForResult(emailLogin, 100)
        }

        // 이메일로 회원가입 버튼
        binding.tvJoinToEmail.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)

        }
    }
}