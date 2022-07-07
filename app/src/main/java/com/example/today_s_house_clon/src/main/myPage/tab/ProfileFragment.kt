package com.example.today_s_house_clon.src.main.myPage.tab


import android.annotation.SuppressLint
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
import com.example.today_s_house_clon.databinding.FragmentProfileBinding
import com.example.today_s_house_clon.src.main.advertisement.HomeAdvertisementAdapter
import com.example.today_s_house_clon.src.main.home.HomeService
import com.example.today_s_house_clon.src.main.home.InterestFragmentInterface
import com.example.today_s_house_clon.src.main.home.models.HomeResponse
import com.example.today_s_house_clon.src.main.myPage.MyPageInterface
import com.example.today_s_house_clon.src.main.myPage.MyPageRetrofitInterface
import com.example.today_s_house_clon.src.main.myPage.MyPageService
import com.example.today_s_house_clon.src.main.myPage.adapter.MyPageAddPhotoRecyclerViewAdapter
import com.example.today_s_house_clon.src.main.myPage.models.MyPageResponse
import com.example.today_s_house_clon.src.main.recyclerView.GridMenuVO
import com.example.today_s_house_clon.src.main.recyclerView.RecyclerViewHeight
import com.example.today_s_house_clon.src.main.recyclerView.RecyclerViewWidth
import com.example.today_s_house_clon.src.main.store.StoreService
import com.example.today_s_house_clon.src.main.store.models.StoreResponse

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile), MyPageInterface, InterestFragmentInterface {

    private lateinit var bannerAdapter: HomeAdvertisementAdapter
    private var photoList = arrayListOf<GridMenuVO>()
    private var bannerPosition = Int.MAX_VALUE/2
    private var bannerHandler = BannerHandler()

    // 2초 후 광고 전환
    private val intervalTime = 2000.toLong()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // api 연동 바로 시작
        val editor = ApplicationClass.sSharedPreferences
        val jwt = editor.getString("jwt", null)
        val userId = editor.getLong("userId", 0)
        showLoadingDialog(requireContext())
        MyPageService(this).tryGetMyPage(jwt!!, userId)
        HomeService(this).tryGetHome(jwt!!)

        setAddPhotoUi()

        // 광고어댑터
        bannerAdapter = HomeAdvertisementAdapter()
        binding.vpMyPage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpMyPage.adapter = bannerAdapter
        binding.vpMyPage.setCurrentItem(bannerPosition, false)

        binding.vpMyPage.apply {
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
    }

    private fun setAddPhotoUi(){
        addPhotoList()
        binding.rvMyPage.adapter = MyPageAddPhotoRecyclerViewAdapter(photoList)
        binding.rvMyPage.layoutManager = GridLayoutManager(requireContext(),3)
        val recyclerviewHeight = RecyclerViewHeight(20)
        val recyclerviewWidth = RecyclerViewWidth(5)
        binding.rvMyPage.addItemDecoration(recyclerviewHeight)
        binding.rvMyPage.addItemDecoration(recyclerviewWidth)
    }

    private fun addPhotoList(){
        photoList.clear()
        photoList.add(GridMenuVO(R.drawable.ic_photo_1, "전체"))
        photoList.add(GridMenuVO(R.drawable.ic_photo_2, "거실"))
        photoList.add(GridMenuVO(R.drawable.ic_photo_3, "침실"))
        photoList.add(GridMenuVO(R.drawable.ic_photo_4, "주방"))
        photoList.add(GridMenuVO(R.drawable.ic_photo_5, "서재&작업실"))
        photoList.add(GridMenuVO(R.drawable.ic_photo_6, "베란다"))
        photoList.add(GridMenuVO(R.drawable.ic_photo_7, "욕실"))
        photoList.add(GridMenuVO(R.drawable.ic_photo_8, "드레스룸"))
        photoList.add(GridMenuVO(R.drawable.ic_photo_9, "가구&소품"))
    }

    @SuppressLint("SetTextI18n")
    override fun onGetMyPageSuccess(response: MyPageResponse) {
        dismissLoadingDialog()
        binding.tvUserNickname.text = response.result.name
        binding.tvFollow.text = "팔로워 ${response.result.followers}"
        binding.tvFollowing.text = "팔로잉 ${response.result.follows}"
        binding.tvLike.text = "좋아요 ${response.result.likes}"
        binding.tvClip.text = "스크랩 ${response.result.scraps}"
        binding.tvOrderCount.text = "${response.result.orderHistory}"
        binding.tvCouponCount.text = "${response.result.coupons}"
        binding.tvPoint.text = "${response.result.points}"
        binding.tvQuestionCount.text = "${response.result.inquiry}"
        binding.tvReviewCount.text = "${response.result.myReviews}"

    }

    override fun onGetMyPageFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패")
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
                binding.vpMyPage.setCurrentItem(++bannerPosition,true)
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
        dismissLoadingDialog()

    }

    override fun onGetHomeFailure(message: String) {
        dismissLoadingDialog()

    }

    override fun onGetStoreSuccess(response: StoreResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetStoreFailure(message: String) {
        TODO("Not yet implemented")
    }

}