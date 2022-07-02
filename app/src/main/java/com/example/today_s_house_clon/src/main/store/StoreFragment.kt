package com.example.today_s_house_clon.src.main.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentHomeBinding
import com.example.today_s_house_clon.databinding.FragmentStoreBinding
import com.example.today_s_house_clon.src.main.home.HomePagerAdapter
import com.example.today_s_house_clon.src.main.home.tab.*
import com.example.today_s_house_clon.src.main.store.tab.StoreHomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StoreFragment : BaseFragment<FragmentStoreBinding>(FragmentStoreBinding::bind, R.layout.fragment_store) {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.storeViewPager
        tabLayout = binding.storeTabLayout

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = StorePagerAdapter(requireActivity())

        // fragment 추가
        pagerAdapter.addFragment(StoreHomeFragment(), "스토어홈")
//        pagerAdapter.addFragment(StoreHomeFragment(), "베스트")
//        pagerAdapter.addFragment(StoreHomeFragment(), "오늘의딜")
//        pagerAdapter.addFragment(StoreHomeFragment(), "7월한정특가")
//        pagerAdapter.addFragment(StoreHomeFragment(), "주말반짝세일")
//        pagerAdapter.addFragment(StoreHomeFragment(),"빠른배송")
//        pagerAdapter.addFragment(StoreHomeFragment(), "오!굿즈")
//        pagerAdapter.addFragment(StoreHomeFragment(),"프리미엄")
//        pagerAdapter.addFragment(StoreHomeFragment(), "기획전")

        // adapter 연결
        viewPager.adapter = pagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL



        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pagerAdapter.tabList[position]
        }.attach()

    }
}