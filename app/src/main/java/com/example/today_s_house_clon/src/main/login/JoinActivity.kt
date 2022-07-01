package com.example.today_s_house_clon.src.main.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
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
    private val emailValidation by lazy { "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" }
    private val passwordValidation by lazy { "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}\$" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        joinEmail = binding.etJoinEmail
        joinPassword = binding.etJoinPassword
        recheckPassword = binding.etJoinPasswordCheck
        joinNickName = binding.etJoinNickname


        // 체크박스 클릭리스너
        binding.joinCheck1.setOnClickListener { onCheckChange(binding.joinCheck1) }
        binding.joinCheck2.setOnClickListener { onCheckChange(binding.joinCheck2) }
        binding.joinCheck3.setOnClickListener { onCheckChange(binding.joinCheck3) }
        binding.joinCheck4.setOnClickListener { onCheckChange(binding.joinCheck4) }
        binding.joinCheck5.setOnClickListener { onCheckChange(binding.joinCheck5) }


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


        // 회원가입 클릭 메서드 활성화 조건
        binding.btnJoin.setOnClickListener {
            // 필수 조건이 안될 경우
            if (!essentialCheckBox() || !essentialInputData()) {
                showCustomToast("필수 사항을 확인해주세요.")
            } else {
                val email = binding.etJoinEmail.text.toString()
                val password = binding.etJoinPassword.text.toString()
                val name = binding.etJoinNickname.text.toString()
                val postRequest = PostSignUpRequest(email = email, name = name, password = password)
                showLoadingDialog(this)
                JoinService(this).tryPostSignUp(postRequest)
                showCustomToast("성공")
            }
        }


    }

    // 약관 동의 체크박스 변경 메서드
    private fun onCheckChange(compoundButton: CompoundButton) {
        when(compoundButton) {
            binding.joinCheck1 -> {
                if (binding.joinCheck1.isChecked) {
                    binding.joinCheck2.isChecked = true
                    binding.joinCheck3.isChecked = true
                    binding.joinCheck4.isChecked = true
                    binding.joinCheck5.isChecked = true
                }else {
                    binding.joinCheck2.isChecked = false
                    binding.joinCheck3.isChecked = false
                    binding.joinCheck4.isChecked = false
                    binding.joinCheck5.isChecked = false

                    binding.llClause.setBackgroundResource(R.drawable.custom_login_input_background_caution)
                    binding.tvJoinClauseCaution.visibility = View.VISIBLE
                }
            }
            // 필수 체크박스 요소 중 하나라도 체크 안된 상태면 경고문 표시
            binding.joinCheck2, binding.joinCheck3, binding.joinCheck4 -> {
                if (binding.joinCheck2.isChecked && binding.joinCheck3.isChecked && binding.joinCheck4.isChecked) {
                    binding.llClause.setBackgroundResource(R.drawable.custom_login_input_background)
                    binding.tvJoinClauseCaution.visibility = View.GONE
                }else {
                    binding.llClause.setBackgroundResource(R.drawable.custom_login_input_background_caution)
                    binding.tvJoinClauseCaution.visibility = View.VISIBLE
                }
            }
            // 전체동의 박스를 체크하지 않더라도 하나하나 다 선택하면 전체 동의도 체크
            else -> {
                binding.joinCheck1.isChecked = (
                        binding.joinCheck2.isChecked
                        && binding.joinCheck3.isChecked
                        && binding.joinCheck4.isChecked
                        && binding.joinCheck5.isChecked)
            }
        }
    }

    // 필수 체크박스 요소 확인
    private fun essentialCheckBox(): Boolean {
        return binding.joinCheck2.isChecked && binding.joinCheck3.isChecked && binding.joinCheck4.isChecked
    }

    // 필수 입력 요소 확인
    private fun essentialInputData():Boolean {
        return checkEmail() && checkPassword() && recheckPassword() && checkNickName()
    }


    // 이메일 형식 확인 메서드
    private fun checkEmail(): Boolean {
        val email = joinEmail.text.toString().trim() // 공백 제거
        val match = Pattern.matches(emailValidation, email) // 이메일 패턴과 일치하는지
        if (match && email.isNotEmpty()) {
            // 이메일 패턴 일치시
            binding.llInputEmail.setBackgroundResource(R.drawable.custom_login_input_background)
            binding.tvJoinInputEmailCaution.visibility = View.GONE
            return true

        } else {
            // 이메일 패턴 불일치
            binding.llInputEmail.setBackgroundResource(R.drawable.custom_login_input_background_caution)
            // 입력에 따른 경고문 다름
            if (email.isEmpty()) {
                binding.tvJoinInputEmailCaution.text = "이메일을 입력해주세요."
            } else {
                binding.tvJoinInputEmailCaution.text = "이메일 형식이 올바르지 않습니다."
            }
            binding.tvJoinInputEmailCaution.visibility = View.VISIBLE // 경고문 띄우기

            return false
        }
    }

    // 비밀번호 형식 확인 메서드
    private fun checkPassword(): Boolean {
        val password = joinPassword.text.toString().trim() // 공백 제거
        val match = Pattern.matches(passwordValidation, password) // 비밀번호 패턴과 일치하는지
        if (match && password.isNotEmpty()) {
            // 비밀번호 패턴 일치시
            binding.llInputPassword.setBackgroundResource(R.drawable.custom_login_input_background)
            binding.tvJoinInputPasswordCaution.visibility = View.GONE

            return true

        } else {
            // 비밀번호 패턴 불일치
            binding.llInputPassword.setBackgroundResource(R.drawable.custom_login_input_background_caution)

            binding.tvJoinInputPasswordCaution.text = "비밀번호는 영문, 숫자를 포함하여 8자 이상이어야 합니다."
            binding.tvJoinInputPasswordCaution.visibility = View.VISIBLE // 경고문 띄우기

            return false
        }
    }

    // 비밀번호 일치 확인 메서드
    private fun recheckPassword(): Boolean {
        val password = recheckPassword.text.toString().trim() // 공백 제거
        val match = password == joinPassword.text.toString() // 비밀번호와 일치하는지

        if (match && password.isNotEmpty()) {
            // 비밀번호 일치시
            binding.llInputPasswordCheck.setBackgroundResource(R.drawable.custom_login_input_background)
            binding.tvJoinInputPasswordCheckCaution.visibility = View.GONE

            return true

        } else {
            // 비밀번호 불일치
            binding.llInputPasswordCheck.setBackgroundResource(R.drawable.custom_login_input_background_caution)
            // 입력에 따른 경고문 다름
            if (password.isEmpty()) {
                binding.tvJoinInputPasswordCheckCaution.text = "확인을 위해 비밀번호를 한 번 더 입력해주세요."
            } else {
                binding.tvJoinInputPasswordCheckCaution.text = "비밀번호가 일치하지 않습니다."
            }
            binding.tvJoinInputPasswordCheckCaution.visibility = View.VISIBLE // 경고문 띄우기

            return false
        }
    }

    // 별명 일치 확인 메서드
    @SuppressLint("SetTextI18n")
    private fun checkNickName(): Boolean {
        val nickName = recheckPassword.text.toString().trim() // 공백 제거

        // 별명길이
        if (nickName.length < 3 || nickName.length > 15 || nickName.isEmpty()) {
            binding.llInputNickname.setBackgroundResource(R.drawable.custom_login_input_background_caution)
            binding.tvJoinInputNicknameCaution.text = "별명을 2~15자 내로 입력해주세요."
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