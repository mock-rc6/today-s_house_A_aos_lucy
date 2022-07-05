package com.example.today_s_house_clon.src.main.myPage

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentMyPageBinding
import com.example.today_s_house_clon.src.main.myPage.tab.MyShoppingFragment
import com.example.today_s_house_clon.src.main.myPage.tab.ProfileFragment
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
        pagerAdapter.addFragment(ProfileFragment(),"프로필")
        pagerAdapter.addFragment(MyShoppingFragment(),"나의쇼핑")

    }
}