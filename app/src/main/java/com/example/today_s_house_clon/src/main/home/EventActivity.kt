package com.example.today_s_house_clon.src.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityEventBinding
import com.example.today_s_house_clon.src.main.home.adapter.EventAdapter
import com.example.today_s_house_clon.src.main.home.models.EventResponse
import com.example.today_s_house_clon.src.main.home.models.HomeResponse
import com.example.today_s_house_clon.src.main.recyclerView.RecyclerViewHeight
import com.example.today_s_house_clon.src.main.store.models.StoreResponse

class EventActivity : BaseActivity<ActivityEventBinding>(ActivityEventBinding::inflate), InterestFragmentInterface {

    private lateinit var banneradapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val editor = ApplicationClass.sSharedPreferences
        val jwt = editor.getString("jwt", null).toString()
        showLoadingDialog(this)
        HomeService(this).tryGetEvent(jwt!!)

        banneradapter = EventAdapter()

        binding.close.setOnClickListener {
            finish()
        }

    }

    private fun setBannerUi(){
        binding.rvEvent.adapter = banneradapter
        binding.rvEvent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val recyclerviewHeight = RecyclerViewHeight(10)
        binding.rvEvent.addItemDecoration(recyclerviewHeight)
    }

    override fun onGetHomeSuccess(response: HomeResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetHomeFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetStoreSuccess(response: StoreResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetStoreFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetEventSuccess(response: EventResponse) {
        dismissLoadingDialog()
        val eventList = response.result.toMutableList()
        banneradapter.addList(eventList)
        setBannerUi()
    }

    override fun onGetEventFailure(message: String) {
        dismissLoadingDialog()
    }

}