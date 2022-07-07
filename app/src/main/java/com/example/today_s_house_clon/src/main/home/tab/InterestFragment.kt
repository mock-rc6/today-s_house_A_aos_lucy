package com.example.today_s_house_clon.src.main.home.tab

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentInterestBinding
import com.example.today_s_house_clon.src.main.advertisement.HomeAdvertisementAdapter
import com.example.today_s_house_clon.src.main.home.*
import com.example.today_s_house_clon.src.main.home.adapter.HomeCategoryRecyclerViewAdapter
import com.example.today_s_house_clon.src.main.home.adapter.HomeRankingRecyclerViewAdapter
import com.example.today_s_house_clon.src.main.home.adapter.HomeTodayDealAdapter
import com.example.today_s_house_clon.src.main.home.adapter.MainHouseInfoRecyclerAdapter
import com.example.today_s_house_clon.src.main.home.models.HomeResponse
import com.example.today_s_house_clon.src.main.recyclerView.*
import com.example.today_s_house_clon.src.main.store.models.StoreResponse


class InterestFragment : BaseFragment<FragmentInterestBinding>(FragmentInterestBinding::bind, R.layout.fragment_interest) , InterestFragmentInterface{

    private lateinit var bannerAdapter: HomeAdvertisementAdapter
    private lateinit var houseAdapter: MainHouseInfoRecyclerAdapter
    private lateinit var dealAdapter: HomeTodayDealAdapter
    private lateinit var rankingAdapter: HomeRankingRecyclerViewAdapter
    private var bannerPosition = Int.MAX_VALUE/2
    private var bannerHandler = BannerHandler()
    private var categoryList = ArrayList<CategoryVO>()
    private var menuList = ArrayList<GridMenuVO>()

    // 2초 후 광고 전환
    private val intervalTime = 2000.toLong()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // api 연동 바로 시작
        val editor = ApplicationClass.sSharedPreferences
        val jwt = editor.getString("jwt", null).toString()
        val userId = editor.getLong("userId", 0)
        showCustomToast("$userId")

        showLoadingDialog(requireContext())
        HomeService(this).tryGetHome(jwt!!)
        HomeService(this).tryGetStore(jwt!!)


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

        // 카테고리 리스트 추가
        addCategoryList()
        binding.rvHomeInterestThird.adapter = HomeCategoryRecyclerViewAdapter(categoryList)
        binding.rvHomeInterestThird.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)

        houseAdapter = MainHouseInfoRecyclerAdapter()
        dealAdapter = HomeTodayDealAdapter()
        rankingAdapter = HomeRankingRecyclerViewAdapter()
    }

    private fun setHouseUI() {
        // 집들이뷰어1 리사이클러뷰
        binding.rvHomeInterestSecond.adapter = houseAdapter
        binding.rvHomeInterestSecond.layoutManager = GridLayoutManager(requireContext(),2)

        // 집들이뷰어2 리사이클러뷰
        binding.rvHomeInterestFourth.adapter = houseAdapter
        binding.rvHomeInterestFourth.layoutManager = GridLayoutManager(requireContext(),2)

        // 랭킹 이미지 리사이클러뷰
        binding.rvHomeInterestVideo.adapter = rankingAdapter
        binding.rvHomeInterestVideo.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setDealUI(){
        // 오늘의 딜 상품 1개
        binding.rvHomeInterestDeal.adapter = dealAdapter
        binding.rvHomeInterestDeal.layoutManager = LinearLayoutManager(requireContext())
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

    // 카테고리 리스트
    private fun addCategoryList() {
        categoryList.clear()
        categoryList.add((CategoryVO(R.drawable.ic_category_1,"가구", "소파,침대...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_2,"패브릭", "침구,커튼...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_3,"가전", "냉장고,노트북...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_4,"유아·아동", "매트,기저귀...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_5,"조명", "스탠드,무드등...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_6,"주방용품", "그릇,냄비...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_7,"데코·식물", "그림,캔들...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_8,"수납·정리", "리빙박스,행거...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_9,"생활용품", "욕실,청소...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_10,"생필품", "세제,화장지...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_11,"공구·DIY", "시트지,손잡이...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_12,"인테리어시공", "주방,욕실...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_13,"반려동물", "사료,패션...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_14,"캠핑용품", "텐트,테이블...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_15,"실내운동", "요가,헬스...")))
        categoryList.add((CategoryVO(R.drawable.ic_category_16,"렌탈", "정수기,비데...")))

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

        val images = response.result.eventInfo.toMutableList()
        bannerAdapter.addImg(images)
        Log.d("TAG", "images size: ${images.size}")

        val house = response.result.mainHouseInfo.toMutableList()
        houseAdapter.addImg(house)
        Log.d("TAG", "house size: ${house.size}")

        val ranking = response.result.mainHouseInfo.toMutableList()
        rankingAdapter.addImg(ranking)

        setHouseUI()
        dismissLoadingDialog()
    }

    override fun onGetHomeFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패")
        Log.d("TAG", "에러내용 : $message")
    }

    override fun onGetStoreSuccess(response: StoreResponse) {

        val deal = response.result.todaysDealList.toMutableList()
        Log.d("TAG", "deal size: ${deal.size}")
        dealAdapter.addImg(deal)

        setDealUI()
        dismissLoadingDialog()
    }

    override fun onGetStoreFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패")
        Log.d("TAG", "에러내용 : $message")
    }

}