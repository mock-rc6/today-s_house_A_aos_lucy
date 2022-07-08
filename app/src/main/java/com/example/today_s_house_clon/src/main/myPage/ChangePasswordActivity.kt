package com.example.today_s_house_clon.src.main.myPage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityChangePasswordBinding
import com.example.today_s_house_clon.src.main.myPage.models.MyPageResponse
import com.example.today_s_house_clon.src.main.myPage.models.PasswordResponse
import com.example.today_s_house_clon.src.main.myPage.models.RequestPassword
import java.util.regex.Pattern

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>(ActivityChangePasswordBinding::inflate), MyPageInterface {

    lateinit var password : TextView
    lateinit var recheckPassword : TextView

    //비밀번호 정규식
    private val passwordValidation by lazy { "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}\$" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        password = binding.etEditPassword
        recheckPassword = binding.etEditPasswordRecheck

        binding.backArrow.setOnClickListener {
            finish()
        }

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // text 변경 전 호출
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkPassword()
            }
            override fun afterTextChanged(p0: Editable?) {
                // text 변경 후 호출
            }
        })
        recheckPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // text 변경 전 호출
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                recheckPassword()
            }
            override fun afterTextChanged(p0: Editable?) {
                // text 변경 후 호출
            }
        })

        binding.btnChange.setOnClickListener {
            if(essentialInputData()) {
                val password = binding.etEditPassword.text.toString()
                val editor = ApplicationClass.sSharedPreferences
                val jwt = editor.getString("jwt", null)
                val userId = editor.getLong("userId", 0)
                val request = RequestPassword(password)
                showLoadingDialog(this)
                MyPageService(this).tryChangePassword(request, jwt!!,userId)
            }
        }


    }

    // 필수 입력 요소 확인
    private fun essentialInputData():Boolean {
        return checkPassword() && recheckPassword()
    }

    // 비밀번호 형식 확인 메서드
    private fun checkPassword(): Boolean {
        val password = password.text.toString().trim() // 공백 제거
        val match = Pattern.matches(passwordValidation, password) // 비밀번호 패턴과 일치하는지
        if (match && password.isNotEmpty()) {
            // 비밀번호 패턴 일치시
            binding.llInputNewPassword.setBackgroundResource(R.drawable.custom_login_input_background)
            binding.llCaution1.visibility = View.GONE

            return true

        } else {
            // 비밀번호 패턴 불일치
            binding.llInputNewPassword.setBackgroundResource(R.drawable.custom_login_input_background_caution)
            binding.llCaution1.visibility = View.VISIBLE // 경고문 띄우기

            return false
        }
    }

    // 비밀번호 일치 확인 메서드
    private fun recheckPassword(): Boolean {
        val pass = recheckPassword.text.toString().trim() // 공백 제거
        val match = pass == password.text.toString() // 비밀번호와 일치하는지

        if (match && pass.isNotEmpty()) {
            // 비밀번호 일치시
            binding.llInputNewPasswordRecheck.setBackgroundResource(R.drawable.custom_login_input_background)
            binding.llCaution2.visibility = View.GONE

            return true

        } else {
            // 비밀번호 불일치
            binding.llInputNewPasswordRecheck.setBackgroundResource(R.drawable.custom_login_input_background_caution)
            binding.llCaution2.visibility = View.VISIBLE // 경고문 띄우기

            return false
        }
    }

    override fun onGetMyPageSuccess(response: MyPageResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetMyPageFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetChangePasswordSuccess(response: PasswordResponse) {
        dismissLoadingDialog()
        showCustomToast("성공")
        finish()
    }

    override fun onGetChangePasswordFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패")
    }
}