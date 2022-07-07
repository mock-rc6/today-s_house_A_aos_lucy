package com.example.today_s_house_clon.src.main.store.tab

import android.content.Intent
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
import com.example.today_s_house_clon.databinding.FragmentStoreHomeBinding
import com.example.today_s_house_clon.src.main.advertisement.StoreAdvertisementAdapter
import com.example.today_s_house_clon.src.main.recyclerView.*
import com.example.today_s_house_clon.src.main.store.ItemDetailsActivity
import com.example.today_s_house_clon.src.main.store.adapter.StoreCategoryRecyclerViewAdapter
import com.example.today_s_house_clon.src.main.store.StoreInterface
import com.example.today_s_house_clon.src.main.store.StoreService
import com.example.today_s_house_clon.src.main.store.adapter.TodaysDealRecyclerAdapter
import com.example.today_s_house_clon.src.main.store.models.DetailResponse
import com.example.today_s_house_clon.src.main.store.models.StoreResponse
import com.example.today_s_house_clon.src.main.store.models.TodayDeal

class StoreHome : BaseFragment<FragmentStoreHomeBinding>(FragmentStoreHomeBinding::bind, R.layout.fragment_store_home), StoreInterface {

    // 광고 인덱스값 > 광고 아이템 수의 절반 중간에서 시작하여 앞뒤 이동시 무한대로 보임
    private var bannerPosition = Int.MAX_VALUE/2
    private var bannerHandler = BannerHandler()
    private lateinit var bannerAdapter : StoreAdvertisementAdapter
    private lateinit var dealAdapter : TodaysDealRecyclerAdapter
    private var menuList = ArrayList<GridMenuVO>()
    private var categoryList = ArrayList<CategoryVO>()

    // 2초 후 광고 전환
    private val intervalTime = 2000.toLong()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // api 연동 바로 시작
        val editor = ApplicationClass.sSharedPreferences
        val jwt = editor.getString("jwt", null)
        showLoadingDialog(requireContext())
        StoreService(this).tryGetStore(jwt!!)


        // 광고어댑터
        bannerAdapter = StoreAdvertisementAdapter()
        binding.vpStoreHomeAdvertisement.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpStoreHomeAdvertisement.adapter = bannerAdapter
        binding.vpStoreHomeAdvertisement.setCurrentItem(bannerPosition, false)

        binding.vpStoreHomeAdvertisement.apply {
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

        // 상단 메뉴 리스트 추가
        addMenuList()
        // 상단 메뉴 어댑터 연결
        binding.rvStoreHomeMenu.adapter = MenuRecyclerViewAdapter(menuList)
        binding.rvStoreHomeMenu.layoutManager = GridLayoutManager(requireContext(),5)

        // 카테고리 리스트 추가
        addCategoryList()
        // 카테고리 어댑터 연결
        binding.rvStoreHomeCategory.adapter = StoreCategoryRecyclerViewAdapter(categoryList)
        binding.rvStoreHomeCategory.layoutManager = GridLayoutManager(requireContext(),4)

        // 오늘의 딜
        dealAdapter = TodaysDealRecyclerAdapter()
        binding.rvStoreHomeTodayDeal.adapter = dealAdapter
        binding.rvStoreHomeTodayDeal.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // 오늘의 딜 아이템 클릭 리스너
        dealAdapter.setOnItemClickListener(object : TodaysDealRecyclerAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: TodayDeal, position: Int) {
                val intent = Intent(requireContext(), ItemDetailsActivity::class.java)
                intent.putExtra("itemId", data.itemId)
                Log.d("TAG", "id형식: ${data.itemId.javaClass.name} / id값: ${data.itemId}")
                startActivity(intent)
//                showCustomToast("${data.itemId}")

            }
        })

    }

    // 메뉴 리스트
    private fun addMenuList() {

        menuList.clear()
        menuList.add(GridMenuVO(R.drawable.ic_store_1, "오세일"))
        menuList.add(GridMenuVO(R.drawable.ic_store_2, "트렌드발견"))
        menuList.add(GridMenuVO(R.drawable.ic_store_3, "리퍼마켓"))
        menuList.add(GridMenuVO(R.drawable.ic_store_4, "프리미엄"))
        menuList.add(GridMenuVO(R.drawable.ic_store_5, "생필품핫딜"))
        menuList.add(GridMenuVO(R.drawable.ic_store_6, "반려동물"))
        menuList.add(GridMenuVO(R.drawable.ic_store_7, "오!굿즈"))
        menuList.add(GridMenuVO(R.drawable.ic_store_8, "유아동"))
        menuList.add(GridMenuVO(R.drawable.ic_store_9, "캠핑용품"))
        menuList.add(GridMenuVO(R.drawable.ic_store_10, "LG쇼룸"))

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
                binding.vpStoreHomeAdvertisement.setCurrentItem(++bannerPosition,true)
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

    override fun onGetStoreSuccess(response: StoreResponse) {
        dismissLoadingDialog()
//        showCustomToast("성공")
        if (response.result.eventImgs.isNotEmpty()) {

            val images = response.result.eventImgs.toMutableList()
            bannerAdapter.addImg(images)
            val deal = response.result.todaysDealList.toMutableList()
            dealAdapter.addImg(deal)

        }
    }

    override fun onGetStoreFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패")
        Log.d("TAG", "에러내용 : $message")
    }

    override fun onGetItemDetailSuccess(response: DetailResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetItemDetailFailure(message: String) {
        TODO("Not yet implemented")
    }

}