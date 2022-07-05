package com.example.today_s_house_clon.src.main.home.tab

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentInterestBinding
import com.example.today_s_house_clon.src.main.advertisement.HomeAdvertisementAdapter
import com.example.today_s_house_clon.src.main.advertisement.StoreAdvertisementAdapter
import com.example.today_s_house_clon.src.main.home.HomeService
import com.example.today_s_house_clon.src.main.home.InterestFragmentInterface
import com.example.today_s_house_clon.src.main.home.models.HomeResponse
import com.example.today_s_house_clon.src.main.recyclerViewAdapter.GridMenuVO
import com.example.today_s_house_clon.src.main.recyclerViewAdapter.MenuRecyclerViewAdapter


class InterestFragment : BaseFragment<FragmentInterestBinding>(FragmentInterestBinding::bind, R.layout.fragment_interest) , InterestFragmentInterface{

    private var menuList = ArrayList<GridMenuVO>()
    private lateinit var bannerAdapter: HomeAdvertisementAdapter
    private var bannerPosition = Int.MAX_VALUE/2
    private var bannerHandler = BannerHandler()

    // 2초 후 광고 전환
    private val intervalTime = 2000.toLong()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // api 연동 바로 시작
        val editor = ApplicationClass.sSharedPreferences
        val jwt = editor.getString("jwt", null)
        // 기 로그인 사용자 토큰정보 확인 후 api연동
        if (jwt != null && jwt.isNotEmpty()) {
            showLoadingDialog(requireContext())
            HomeService(this, jwt).tryGetHome()
        }


        // 광고어댑터
        bannerAdapter = HomeAdvertisementAdapter()
        binding.vpHomeInterestAdvertisement.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpHomeInterestAdvertisement.adapter = bannerAdapter
        binding.vpHomeInterestAdvertisement.setCurrentItem(bannerPosition, false)

        binding.vpHomeInterestAdvertisement.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        // 뷰페이저가 움직이는 중일 때 자동 스크롤 시작
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                        // 자동 스크롤 멈춤
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)
                    }
                }
            })
        }

        // 메뉴 리스트 추가
        addMenuList()
        // 상단 메뉴 어댑터 연결
        binding.rvHomeInterestFirst.adapter = MenuRecyclerViewAdapter(menuList)
        binding.rvHomeInterestFirst.layoutManager = GridLayoutManager(requireContext(),5)

    }

    // 메뉴 리스트
    private fun addMenuList() {

        menuList.clear()
        menuList.add(GridMenuVO(R.drawable.ic_interest_1, "쇼핑하기"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_2, "빠른배송"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_3, "N평집들이"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_4, "공간별사진"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_5, "리모델링"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_6, "쉬운이사"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_7, "오늘의딜"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_8, "누르면할인"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_9, "라스트세일"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_10, "집에서뭐해?"))

    }

    // 광고 자동 스크롤 시작 함수
    private fun autoScrollStart(intervalTime: Long) {
        bannerHandler.removeMessages(0)
        // intervalTime 만큼 핸들러 실행
        bannerHandler.sendEmptyMessageDelayed(0, intervalTime)
    }

    // 광고 자동 스크롤 정지 함수
    private fun autoScrollStop(){
        bannerHandler.removeMessages(0)
    }

    // 배너 자동 스크롤 컨트롤 핸들러
    private inner class BannerHandler: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what == 0) {
                // 다음 페이지 이동
                binding.vpHomeInterestAdvertisement.setCurrentItem(++bannerPosition,true)
                // 자동 스크롤
                autoScrollStart(intervalTime)
            }
        }
    }

    // 다른 화면 갔다가 돌아오면 자동 스크롤 시작
    override fun onResume() {
        super.onResume()
        autoScrollStart(intervalTime)
    }

    // 다른 화면 볼때 자동 스크롤 중지
    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

    override fun onGetHomeSuccess(response: HomeResponse) {
        dismissLoadingDialog()
        showCustomToast("성공")
        if (response.result.eventInfo.isNotEmpty()) {

            val images = response.result.eventInfo.toMutableList()
            bannerAdapter.addImg(images)


        }
    }

    override fun onGetHomeFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패")
        Log.d("TAG", "에러내용 : $message")
    }
}