package com.example.today_s_house_clon.main.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.today_s_house_clon.databinding.ActivityJoinMembershipBinding
import com.example.today_s_house_clon.main.MainActivity

class JoinMembershipActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinMembershipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinMembershipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKakaoLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvLoginToEmail.setOnClickListener {

        }

        binding.tvJoinToEmail.setOnClickListener {


        }
    }
}