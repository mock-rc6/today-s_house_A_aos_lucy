package com.example.today_s_house_clon.src.main.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.isInvisible
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityJoinBinding
import com.example.today_s_house_clon.src.main.login.models.PostSignUpRequest
import com.example.today_s_house_clon.src.main.login.models.SignUpResponse
import java.util.regex.Pattern

class JoinActivity : BaseActivity<ActivityJoinBinding>(ActivityJoinBinding::inflate),
JoinActivityInterface{

    lateinit var joinEmail : TextView
    lateinit var joinPassword : TextView
    lateinit var recheckPassword : TextView
    lateinit var joinNickName : TextView

    // 입력값 확인 정규식
    val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    val passwordValidation = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}\$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        joinEmail = binding.etJoinEmail
        joinPassword = binding.etJoinPassword
        recheckPassword = binding.etJoinPasswordCheck
        joinNickName = binding.etJoinNickname

        joinEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // text 변경 전 호출
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkEmail()
            }
            override fun afterTextChanged(p0: Editable?) {
                // text 변경 후 호출
            }
        })
        joinPassword.addTextChangedListener(object : TextWatcher{
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
        recheckPassword.addTextChangedListener(object : TextWatcher{
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
        joinNickName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // text 변경 전 호출
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkNickName()
            }
            override fun afterTextChanged(p0: Editable?) {
                // text 변경 후 호출
            }
        })


        binding.btnJoin.setOnClickListener {
            val email = binding.etJoinEmail.text.toString()
            val password = binding.etJoinPassword.text.toString()
            val name = binding.etJoinNickname.text.toString()
            val postRequest = PostSignUpRequest(email = email, name = name, password = password)
            showLoadingDialog(this)
            JoinService(this).tryPostSignUp(postRequest)
        }


    }


    // 이메일 형식 확인 메서드
    fun checkEmail(): Boolean {
        var email = joinEmail.text.toString().trim() // 공백 제거
        val match = Pattern.matches(emailValidation, email) // 이메일 패턴과 일치하는지
        if (match) {
            // 이메일 패턴 일치시
            binding.llInputEmail.setBackgroundResource(R.drawable.custom_login_input_background)
            binding.tvJoinInputEmailCaution.visibility = View.GONE
            return true

        } else {
            // 이메일 패턴 불일치
            binding.llInputEmail.setBackgroundResource(R.drawable.custom_login_input_background_caution)
            // 입력에 따른 경고문 다름
            if (email.length.equals(0)) {
                binding.tvJoinInputEmailCaution.setText("이메일을 입력해주세요.")
            } else {
                binding.tvJoinInputEmailCaution.setText("이메일 형식이 올바르지 않습니다.")
            }
            binding.tvJoinInputEmailCaution.visibility = View.VISIBLE // 경고문 띄우기

            return false
        }
    }

    // 비밀번호 형식 확인 메서드
    fun checkPassword(): Boolean {
        var password = joinPassword.text.toString().trim() // 공백 제거
        val match = Pattern.matches(passwordValidation, password) // 비밀번호 패턴과 일치하는지
        if (match) {
            // 비밀번호 패턴 일치시
            binding.llInputPassword.setBackgroundResource(R.drawable.custom_login_input_background)
            binding.tvJoinInputPasswordCaution.visibility = View.GONE

            return true

        } else {
            // 비밀번호 패턴 불일치
            binding.llInputPassword.setBackgroundResource(R.drawable.custom_login_input_background_caution)

            binding.tvJoinInputPasswordCaution.setText("비밀번호는 영문, 숫자를 포함하여 8자 이상이어야 합니다.")
            binding.tvJoinInputPasswordCaution.visibility = View.VISIBLE // 경고문 띄우기

            return false
        }
    }

    // 비밀번호 일치 확인 메서드
    fun recheckPassword(): Boolean {
        var password = recheckPassword.text.toString().trim() // 공백 제거
        val match = password.equals(joinPassword.text.toString()) // 비밀번호와 일치하는지
        Log.d("TAG", "비번: ${joinPassword.text.toString()} / 확인: ${password}")
        if (match) {
            // 비밀번호 일치시
            binding.llInputPasswordCheck.setBackgroundResource(R.drawable.custom_login_input_background)
            binding.tvJoinInputPasswordCheckCaution.visibility = View.GONE

            return true

        } else {
            // 비밀번호 불일치
            binding.llInputPasswordCheck.setBackgroundResource(R.drawable.custom_login_input_background_caution)
            // 입력에 따른 경고문 다름
            if (password.length.equals(0)) {
                binding.tvJoinInputPasswordCheckCaution.setText("확인을 위해 비밀번호를 한 번 더 입력해주세요.")
            } else {
                binding.tvJoinInputPasswordCheckCaution.setText("비밀번호가 일치하지 않습니다.")
            }
            binding.tvJoinInputPasswordCheckCaution.visibility = View.VISIBLE // 경고문 띄우기

            return false
        }
    }

    // 별명 일치 확인 메서드
    fun checkNickName(): Boolean {
        var nickName = recheckPassword.text.toString().trim() // 공백 제거

        // 별명길이
        if (nickName.length < 3 || nickName.length > 15) {
            binding.llInputNickname.setBackgroundResource(R.drawable.custom_login_input_background_caution)
            binding.tvJoinInputNicknameCaution.setText("별명을 2~15자 내로 입력해주세요.")
            binding.tvJoinInputNicknameCaution.visibility = View.VISIBLE // 경고문 띄우기

            return false
        } else {
            binding.llInputNickname.setBackgroundResource(R.drawable.custom_login_input_background)
            binding.tvJoinInputNicknameCaution.visibility = View.GONE

            return true
        }
    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()
        val id = response.result.userId
        val jwt = response.result.jwt


    }

    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        Log.d("TAG", "오류 : $message")
        showCustomToast("오류 : $message")
    }
}