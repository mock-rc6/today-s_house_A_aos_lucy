package com.example.today_s_house_clon.src.main

import android.os.Bundle
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityMainBinding
import com.example.today_s_house_clon.src.main.home.HomeFragment
import com.example.today_s_house_clon.src.main.homeService.HomeServiceFragment
import com.example.today_s_house_clon.src.main.myPage.MyPageFragment
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
                    }
                    R.id.store -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, StoreFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.home_service -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, HomeServiceFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.mypage -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }

            // 처음 시작시 홈화면이 제일 먼저 시작
            selectedItemId = R.id.home
        }


    }



}