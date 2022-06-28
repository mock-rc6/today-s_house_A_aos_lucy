package com.example.today_s_house_clon.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container,false)
        val view = binding.root
        viewPager = binding.homeViewPager
        tabLayout = binding.homeTabLayout

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = HomePagerAdapter(requireActivity())

        // fragment 추가
        pagerAdapter.addFragment(InterestFragment(), "인기")
        pagerAdapter.addFragment(FollowingFragment(), "팔로잉")
        pagerAdapter.addFragment(PhotoFragment(), "사진")
        pagerAdapter.addFragment(HousewarmingFragment(), "집들이")
        pagerAdapter.addFragment(KnowHowFragment(), "노하우")
        pagerAdapter.addFragment(ExpertHousewarmingFragment(),"전문가집들이")
        pagerAdapter.addFragment(QuestionNAnswerFragment(), "질문과답변")

        // adapter 연결
        viewPager.adapter = pagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL



        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pagerAdapter.tabList[position]
        }.attach()
    }
}