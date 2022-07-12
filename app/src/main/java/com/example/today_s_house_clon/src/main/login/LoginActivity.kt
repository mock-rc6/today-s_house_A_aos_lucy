package com.example.today_s_house_clon.src.main.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityLoginBinding
import com.example.today_s_house_clon.src.main.MainActivity
import com.example.today_s_house_clon.src.main.login.models.LoginResponse
import com.example.today_s_house_clon.src.main.login.models.PostLoginRequest

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginActivityInterface {

    lateinit var loginEmail : TextView
    lateinit var loginPassword : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginEmail = binding.etLoginEmail
        loginPassword = binding.etLoginPassword

        // 이메일 비밀번호 입력 후 로그인시 입력 값 전달
        binding.btnLogin.setOnClickListener {

            // 입력란에 내용에 따라 로그인 버튼 활성화 비활성화로 나눔
            if (checkEmpty(loginEmail)) {
                showCustomToast("이메일을 입력해 주세요.")
            } else if(checkEmpty(loginPassword)) {
                showCustomToast("비밀번호를 입력해 주세요.")
            } else {
                val email = loginEmail.text.toString()
                val password = loginPassword.text.toString()
                val postRequest = PostLoginRequest(email = email, password = password)
                showLoadingDialog(this)
                LoginService(this).tryPostLogin(postRequest)
            }
        }

        // 뒤로가기 버튼
        binding.btnLoginBackArrow.setOnClickListener {
            finish()
        }

        loginEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // text 변경 전 호출
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkEmpty(loginPassword) || checkEmpty(loginEmail)) {
                    binding.btnLogin.setBackgroundResource(R.drawable.custom_radius_corner_btn_color_main_before_select)
                }else {
                    binding.btnLogin.setBackgroundResource(R.drawable.custom_radius_corner_btn_color_main)
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                // text 변경 후 호출
            }
        })

        loginPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // text 변경 전 호출
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkEmpty(loginPassword) || checkEmpty(loginEmail)) {
                    binding.btnLogin.setBackgroundResource(R.drawable.custom_radius_corner_btn_color_main_before_select)
                }else {
                    binding.btnLogin.setBackgroundResource(R.drawable.custom_radius_corner_btn_color_main)
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                // text 변경 후 호출
            }
        })

    }

    // 입력란 확인 메서드
    private fun checkEmpty(input: TextView): Boolean{
        val content = input.text.toString()
        return content.isEmpty()
    }

    @SuppressLint("CommitPrefEdits")
    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()

        // 아이디 비밀번호 확인이 완료 될 때 만 화면 전환
        if(response.code == 1000) {
            // 생성된 jwt sp에 저장
            val jwt = response.result.jwt
            val userId = response.result.userId
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString("jwt", jwt)
            editor.putLong("userId", userId)
            // 어플 재실행시 로그인 여부 저장
            editor.putBoolean("login", true)
            editor.apply()
            // 액티비티 하나만 실행되고 나머지는 다 삭제
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        } else {
            showCustomToast("아이디 또는 비밀번호가\n잘못되었습니다.")
        }

    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        Log.d("TAG", "오류 : $message")
    }
}