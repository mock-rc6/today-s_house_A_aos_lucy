package com.example.today_s_house_clon.src.main.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityItemDetailsBinding
import com.example.today_s_house_clon.databinding.ActivityMainBinding
import com.example.today_s_house_clon.src.main.store.models.DetailResponse
import com.example.today_s_house_clon.src.main.store.models.StoreResponse

class ItemDetailsActivity : BaseActivity<ActivityItemDetailsBinding>(ActivityItemDetailsBinding::inflate), StoreInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemId = intent.getLongExtra("itemId", -1).toString()
        val userId = ApplicationClass.sSharedPreferences.getLong("userId", -1).toString()
        showLoadingDialog(this)
        StoreService(this).tryGetItemDetail(itemId, userId)



    }

    override fun onGetItemDetailSuccess(response: DetailResponse) {
        dismissLoadingDialog()
        showCustomToast("성공")
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