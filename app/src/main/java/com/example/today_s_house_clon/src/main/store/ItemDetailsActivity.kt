package com.example.today_s_house_clon.src.main.store

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityItemDetailsBinding
import com.example.today_s_house_clon.src.main.recyclerView.RecyclerViewHeight
import com.example.today_s_house_clon.src.main.recyclerView.RecyclerViewWidth
import com.example.today_s_house_clon.src.main.store.adapter.ItemDetailImagePagerAdapter
import com.example.today_s_house_clon.src.main.store.adapter.ItemDetailViewRecyclerViewAdapter
import com.example.today_s_house_clon.src.main.store.adapter.ReviewImageRecyclerViewAdapter
import com.example.today_s_house_clon.src.main.store.adapter.ReviewRecyclerAdapter
import com.example.today_s_house_clon.src.main.store.models.DetailResponse
import com.example.today_s_house_clon.src.main.store.models.StoreResponse

class ItemDetailsActivity : BaseActivity<ActivityItemDetailsBinding>(ActivityItemDetailsBinding::inflate), StoreInterface {

    private lateinit var itemImageAdapter: ItemDetailImagePagerAdapter
    private lateinit var reviewImageImageAdapter: ReviewImageRecyclerViewAdapter
    private lateinit var detailImageAdapter: ItemDetailViewRecyclerViewAdapter
    private lateinit var reviewAdapter: ReviewRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemId = intent.getLongExtra("itemId", 0)
        val userId = ApplicationClass.sSharedPreferences.getLong("userId", 0)
        val jwt = ApplicationClass.sSharedPreferences.getString("jwt", null)
        showLoadingDialog(this)
        StoreService(this).tryGetItemDetail(jwt!!, itemId, userId)

        itemImageAdapter = ItemDetailImagePagerAdapter()
        reviewImageImageAdapter = ReviewImageRecyclerViewAdapter()
        detailImageAdapter = ItemDetailViewRecyclerViewAdapter()
        reviewAdapter = ReviewRecyclerAdapter()
        // 구매하기 클릭 리스너

    }

    private fun setDetailImageUi(){
        // 상단 상품 이미지 뷰
        binding.vpDetailItem.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpDetailItem.adapter = itemImageAdapter

        // 상품 스타일링 이미지 뷰
        binding.rvDetailStyling.adapter = reviewImageImageAdapter
        binding.rvDetailStyling.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        val recyclerviewHeight = RecyclerViewHeight(10)
        val recyclerviewWidth = RecyclerViewWidth(3)
        binding.rvDetailStyling.addItemDecoration(recyclerviewHeight)
        binding.rvDetailStyling.addItemDecoration(recyclerviewWidth)

        // 상품 상세 이미지 뷰
        binding.rvDetailItemList.adapter = detailImageAdapter
        binding.rvDetailItemList.layoutManager = LinearLayoutManager(this)

        // 리뷰 리사이클러뷰
        binding.rvDetailReview.adapter = reviewAdapter
        binding.rvDetailReview.layoutManager = LinearLayoutManager(this)
    }

    override fun onGetItemDetailSuccess(response: DetailResponse) {
        dismissLoadingDialog()
        val imageList = response.result.imgList
        Log.d("TAG", "${imageList.size}")
        itemImageAdapter.addImg(imageList)
        binding.tvDetailItemName1.text = response.result.itemName
        binding.tvDetailItemName2.text = response.result.itemName
        binding.tvDetailCompanyName.text = response.result.companyName
        binding.tvDetailCompanyName2.text = response.result.companyName
        binding.detailDue.text = intent.getStringExtra("due")
        binding.tvDetailDue.text = response.result.saleRate
        binding.tvDetailPrice.text = response.result.price
        reviewImageImageAdapter.addReviewImageList(response.result.reviewList.toMutableList())
        binding.tvDetailStyleTitleCount.text = ApplicationClass.sSharedPreferences.getString("imgCnt", "0")
        detailImageAdapter.addImage(imageList)
        reviewAdapter.addReview(response.result.reviewList.toMutableList())
        binding.tvDetailReviewCount.text = response.result.reviewList.size.toString()
        binding.btnDetailMoreReview.text = String.format("리뷰 더보기 ( %d )", response.result.reviewList.size )

        setDetailImageUi()
    }

    override fun onGetItemDetailFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패")
    }


    override fun onGetStoreSuccess(response: StoreResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetStoreFailure(message: String) {
        TODO("Not yet implemented")
    }

}