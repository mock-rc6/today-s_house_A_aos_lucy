package com.example.today_s_house_clon.src.main.store.tab

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentStoreHomeBinding
import com.example.today_s_house_clon.src.main.advertisement.AdvertisementAdapter
import com.example.today_s_house_clon.src.main.recyclerViewAdapter.GridMenuVo
import com.example.today_s_house_clon.src.main.recyclerViewAdapter.MenuRecyclerViewAdapter
import com.example.today_s_house_clon.src.main.store.StoreFragmentInterface
import com.example.today_s_house_clon.src.main.store.StoreService
import com.example.today_s_house_clon.src.main.store.models.EventImg
import com.example.today_s_house_clon.src.main.store.models.StoreResponse

class StoreHomeFragment : BaseFragment<FragmentStoreHomeBinding>(FragmentStoreHomeBinding::bind, R.layout.fragment_store_home), StoreFragmentInterface {

    // 광고 인덱스값 > 광고 아이템 수의 절반 중간에서 시작하여 앞뒤 이동시 무한대로 보임
    private var bannerPosition = Int.MAX_VALUE/2
    private var bannerHandler = BannerHandler()
    private lateinit var bannerAdapter : AdvertisementAdapter
    private var menuList = ArrayList<GridMenuVo>()

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
            StoreService(this, jwt).tryGetStore()
        }


        // 광고어댑터
        bannerAdapter = AdvertisementAdapter()
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
        binding.rvStoreHomeFirst.adapter = MenuRecyclerViewAdapter(menuList)
        binding.rvStoreHomeFirst.layoutManager = GridLayoutManager(requireContext(),5)


    }

    // 메뉴 리스트
    private fun addMenuList() {

        menuList.add(GridMenuVo(R.drawable.ic_store_1, "오세일"))
        menuList.add(GridMenuVo(R.drawable.ic_store_2, "트렌드발견"))
        menuList.add(GridMenuVo(R.drawable.ic_store_3, "리퍼마켓"))
        menuList.add(GridMenuVo(R.drawable.ic_store_4, "프리미엄"))
        menuList.add(GridMenuVo(R.drawable.ic_store_5, "생필품핫딜"))
        menuList.add(GridMenuVo(R.drawable.ic_store_6, "반려동물"))
        menuList.add(GridMenuVo(R.drawable.ic_store_7, "오!굿즈"))
        menuList.add(GridMenuVo(R.drawable.ic_store_8, "유아동"))
        menuList.add(GridMenuVo(R.drawable.ic_store_9, "캠핑용품"))
        menuList.add(GridMenuVo(R.drawable.ic_store_10, "LG쇼룸"))

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
        showCustomToast("성공")
        if (response.result.eventImgs.isNotEmpty()) {

            val images = response.result.eventImgs.toMutableList()
            bannerAdapter.addImg(images)

        }
    }

    override fun onGetStoreFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패")
        Log.d("TAG", "에러내용 : $message")
    }

}