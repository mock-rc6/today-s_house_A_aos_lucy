package com.example.today_s_house_clon.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.today_s_house_clon.src.main.login.JoinMembershipActivity
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivitySplashBinding
import com.example.today_s_house_clon.src.main.MainActivity


class Splash : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val login = ApplicationClass.sSharedPreferences.getBoolean("login", false)
        val editor = ApplicationClass.sSharedPreferences.edit()
//        editor.remove("login")
//        editor.remove("jwt")
//        editor.clear()
//        editor.apply()

        // 로그인 여부에 따라 splash 화면 뒤 보여지는 activity 다름
        if (!login) {
            // 로그인을 아직 안했을 때
            Handler(Looper.getMainLooper()).postDelayed({
                val join = Intent(this, JoinMembershipActivity::class.java)
                startActivity(join)
                finish()
            }, DURATION)

        }else {
            // 로그인 정보있을시 메인화면 이동
            Handler(Looper.getMainLooper()).postDelayed({
                val main = Intent(this, MainActivity::class.java)
                startActivity(main)
                finish()
            }, DURATION)

        }

    }

    companion object {
        private const val DURATION : Long = 3000
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}