package com.example.today_s_house_clon.src.main.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewpager2.widget.ViewPager2
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityItemDetailsBinding
import com.example.today_s_house_clon.databinding.ActivityMainBinding
import com.example.today_s_house_clon.src.main.store.adapter.ItemDetailImagePagerAdapter
import com.example.today_s_house_clon.src.main.store.models.DetailResponse
import com.example.today_s_house_clon.src.main.store.models.StoreResponse

class ItemDetailsActivity : BaseActivity<ActivityItemDetailsBinding>(ActivityItemDetailsBinding::inflate), StoreInterface {

    private lateinit var itemImageAdapter: ItemDetailImagePagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemId: Long = intent.getLongExtra("itemId", 0).toLong()
        val userId: Long = ApplicationClass.sSharedPreferences.getLong("userId", 0).toLong()
        val jwt = ApplicationClass.sSharedPreferences.getString("jwt", null)
        showLoadingDialog(this)
        StoreService(this).tryGetItemDetail(jwt!!, itemId, userId)

        itemImageAdapter = ItemDetailImagePagerAdapter()

    }

    private fun setDetailImageUi(){
        binding.vpDetailItem.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpDetailItem.adapter = itemImageAdapter
    }

    override fun onGetItemDetailSuccess(response: DetailResponse) {
        dismissLoadingDialog()
        val imageList = response.result.imgList
        Log.d("TAG", "${imageList.size}")
        itemImageAdapter.addImg(imageList)

        setDetailImageUi()
    }

    override fun onGetItemDetailFailure(message: String) {
        dismissLoadingDialog()

    }


    override fun onGetStoreSuccess(response: StoreResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetStoreFailure(message: String) {
        TODO("Not yet implemented")
    }

}