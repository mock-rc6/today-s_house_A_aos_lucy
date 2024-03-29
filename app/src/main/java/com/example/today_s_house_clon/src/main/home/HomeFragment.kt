package com.example.today_s_house_clon.src.main.home

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentHomeBinding
import com.example.today_s_house_clon.src.main.home.adapter.HomePagerAdapter
import com.example.today_s_house_clon.src.main.home.tab.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var pagerAdapter: HomePagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.homeViewPager
        tabLayout = binding.homeTabLayout

        // 뷰페이저 스크롤 막기
        viewPager.isUserInputEnabled = false

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pagerAdapter = HomePagerAdapter(requireActivity())

        // tab 프래그먼트 추가
        addFragment()

        // adapter 연결
        viewPager.adapter = pagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pagerAdapter.tabList[position]
        }.attach()
    }

    private fun addFragment(){
        pagerAdapter.addFragment(InterestFragment(), "인기")
        pagerAdapter.addFragment(HomeAnotherFragment(), "팔로잉")
        pagerAdapter.addFragment(HomeAnotherFragment(), "사진")
        pagerAdapter.addFragment(HomeAnotherFragment(), "집들이")
        pagerAdapter.addFragment(HomeAnotherFragment(), "노하우")
        pagerAdapter.addFragment(HomeAnotherFragment(),"전문가집들이")
        pagerAdapter.addFragment(HomeAnotherFragment(), "질문과답변")
    }
}