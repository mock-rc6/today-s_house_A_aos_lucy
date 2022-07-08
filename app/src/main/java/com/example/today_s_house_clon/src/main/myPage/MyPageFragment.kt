package com.example.today_s_house_clon.src.main.myPage

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentMyPageBinding
import com.example.today_s_house_clon.src.main.myPage.models.MyPageResponse
import com.example.today_s_house_clon.src.main.myPage.models.PasswordResponse
import com.example.today_s_house_clon.src.main.myPage.tab.MyShoppingFragment
import com.example.today_s_house_clon.src.main.myPage.tab.ProfileFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page), MyPageInterface {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var pagerAdapter: MyPagePagerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.myPageTabLayout
        viewPager = binding.myPageViewPager

        val editor = ApplicationClass.sSharedPreferences
        val jwt = editor.getString("jwt", null)
        val userId = ApplicationClass.sSharedPreferences.getLong("userId", -1)
        showLoadingDialog(requireContext())
        MyPageService(this).tryGetMyPage(jwt!!, userId)




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

    override fun onGetMyPageSuccess(response: MyPageResponse) {
        dismissLoadingDialog()
        showCustomToast("${response.result.name}")

    }

    override fun onGetMyPageFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패")
    }

    override fun onGetChangePasswordSuccess(response: PasswordResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetChangePasswordFailure(message: String) {
        TODO("Not yet implemented")
    }
}