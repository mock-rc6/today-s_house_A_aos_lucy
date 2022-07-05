package com.example.today_s_house_clon.src.main.myPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentHomeBinding
import com.example.today_s_house_clon.databinding.FragmentMyPageBinding
import com.example.today_s_house_clon.databinding.FragmentStoreHomeBinding
import com.example.today_s_house_clon.src.main.home.HomePagerAdapter
import com.example.today_s_house_clon.src.main.myPage.tab.MyShoppingFragment
import com.example.today_s_house_clon.src.main.myPage.tab.ProfilFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var pagerAdapter: MyPagePagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.myPageTabLayout
        viewPager = binding.myPageViewPager

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pagerAdapter = MyPagePagerAdapter(requireActivity())

        addFragment()

        viewPager.adapter = pagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pagerAdapter.tabList[position]
        }.attach()

    }

    private fun addFragment(){
        pagerAdapter.addFragment(MyShoppingFragment(),"프로필")
        pagerAdapter.addFragment(ProfilFragment(),"나의쇼핑")
    }
}