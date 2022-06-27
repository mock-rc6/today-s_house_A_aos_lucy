package com.example.today_s_house_clon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.today_s_house_clon.databinding.ActivityMainBinding

private const val TAG_HOME = "home_fragment"
private const val TAG_STORE = "store_fragment"
private const val TAG_HOME_SERVICE = "home_service_fragment"
private const val TAG_MY_PAGE = "my_page_fragment"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 기존에 등록 된 menu tint 초기화
        binding.mainBottomNav.itemIconTintList = null

        // 처음시작 프래그먼트
        setFragment(TAG_HOME,HomeFragment())

        // 프래그먼트 클릭리스너
        binding.mainBottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> setFragment(TAG_HOME, HomeFragment())
                R.id.store -> setFragment(TAG_STORE, StoreFragment())
                R.id.home_service -> setFragment(TAG_HOME_SERVICE, HomeServiceFragment())
                R.id.mypage -> setFragment(TAG_MY_PAGE, MyPageFragment())
            }
            true
        }


    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager : FragmentManager = supportFragmentManager
        val ft : FragmentTransaction = manager.beginTransaction()

        // 트랜잭션에 tag로 전달된 프래그먼트가 없을 시 add
        if (manager.findFragmentByTag(tag) == null) {
            ft.add(R.id.fl_container, fragment, tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val store = manager.findFragmentByTag(TAG_STORE)
        val homeService = manager.findFragmentByTag(TAG_HOME_SERVICE)
        val myPage = manager.findFragmentByTag(TAG_MY_PAGE)

        // 모든 fragment 숨김
        if (home != null) {
            ft.hide(home)
        }
        if (store != null) {
            ft.hide(store)
        }
        if (homeService != null) {
            ft.hide(homeService)
        }
        if (myPage != null) {
            ft.hide(myPage)
        }

        // 선택한 fragment만 show
        if (tag == TAG_HOME) {
            if (home != null) {
                ft.show(home)
            }
        }else if (tag == TAG_STORE) {
            if (store != null) {
                ft.show(store)
            }
        }else if (tag == TAG_HOME_SERVICE) {
            if (homeService != null) {
                ft.show(homeService)
            }
        }else if (tag == TAG_MY_PAGE) {
            if (myPage != null) {
                ft.show(myPage)
            }
        }

        ft.commitAllowingStateLoss()

    }


}