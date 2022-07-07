package com.example.today_s_house_clon.src.main.store

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentStoreBinding
import com.example.today_s_house_clon.src.main.store.adapter.StorePagerAdapter
import com.example.today_s_house_clon.src.main.store.tab.StoreAnotherFragment
import com.example.today_s_house_clon.src.main.store.tab.StoreHome
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StoreFragment : BaseFragment<FragmentStoreBinding>(FragmentStoreBinding::bind, R.layout.fragment_store) {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var pagerAdapter: StorePagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.storeViewPager
        tabLayout = binding.storeTabLayout

        viewPager.isUserInputEnabled = false

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pagerAdapter = StorePagerAdapter(requireActivity())

        // fragment 추가
        addFragment()
        // adapter 연결
        viewPager.adapter = pagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pagerAdapter.tabList[position]
        }.attach()

    }

    private fun addFragment(){
        pagerAdapter.addFragment(StoreHome(), "스토어홈")
        pagerAdapter.addFragment(StoreAnotherFragment(), "베스트")
        pagerAdapter.addFragment(StoreAnotherFragment(), "오늘의딜")
        pagerAdapter.addFragment(StoreAnotherFragment(), "7월한정특가")
        pagerAdapter.addFragment(StoreAnotherFragment(), "주말반짝세일")
        pagerAdapter.addFragment(StoreAnotherFragment(),"빠른배송")
        pagerAdapter.addFragment(StoreAnotherFragment(), "오!굿즈")
        pagerAdapter.addFragment(StoreAnotherFragment(),"프리미엄")
        pagerAdapter.addFragment(StoreAnotherFragment(), "기획전")
    }
}