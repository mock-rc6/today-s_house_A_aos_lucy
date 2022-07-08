package com.example.today_s_house_clon.src.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityMainBinding
import com.example.today_s_house_clon.src.main.basket.BasketActivity
import com.example.today_s_house_clon.src.main.home.HomeFragment
import com.example.today_s_house_clon.src.main.homeService.HomeServiceFragment
import com.example.today_s_house_clon.src.main.myPage.MyPageFragment
import com.example.today_s_house_clon.src.main.myPage.SettingActivity
import com.example.today_s_house_clon.src.main.store.StoreFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 기존에 등록 된 menu tint 초기화
        binding.mainBottomNav.itemIconTintList = null

        binding.mainBottomNav.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, HomeFragment())
                            .commitAllowingStateLoss()
                        binding.btnMenu.visibility = View.GONE
                        binding.etInput.visibility = View.VISIBLE
                        binding.btnClip.visibility = View.VISIBLE
                        binding.abSpace.visibility = View.GONE
                        binding.btnSetting.visibility = View.GONE
                        binding.btnShare.visibility = View.GONE
                        binding.etInput.hint = "오늘의 집 통합검색"
                    }
                    R.id.store -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, StoreFragment())
                            .commitAllowingStateLoss()
                        binding.btnMenu.visibility = View.VISIBLE
                        binding.etInput.visibility = View.VISIBLE
                        binding.btnClip.visibility = View.VISIBLE
                        binding.abSpace.visibility = View.GONE
                        binding.btnSetting.visibility = View.GONE
                        binding.btnShare.visibility = View.GONE
                        binding.etInput.hint = "스토어 검색"
                    }
                    R.id.home_service -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, HomeServiceFragment())
                            .commitAllowingStateLoss()
                        binding.btnMenu.visibility = View.GONE
                        binding.etInput.visibility = View.VISIBLE
                        binding.btnClip.visibility = View.VISIBLE
                        binding.abSpace.visibility = View.GONE
                        binding.btnSetting.visibility = View.GONE
                        binding.btnShare.visibility = View.GONE
                        binding.etInput.hint = "홈서비스 검색"
                    }
                    R.id.mypage -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, MyPageFragment())
                            .commitAllowingStateLoss()
                        binding.btnMenu.visibility = View.GONE
                        binding.etInput.visibility = View.GONE
                        binding.btnClip.visibility = View.GONE
                        binding.abSpace.visibility = View.VISIBLE
                        binding.btnSetting.visibility = View.VISIBLE
                        binding.btnShare.visibility = View.VISIBLE
                    }
                }
                true
            }

            // 처음 시작시 홈화면이 제일 먼저 시작
            selectedItemId = R.id.home
        }

        binding.btnBasket.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }

        binding.btnSetting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}