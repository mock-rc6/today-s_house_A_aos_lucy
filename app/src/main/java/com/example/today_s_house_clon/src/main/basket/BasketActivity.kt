package com.example.today_s_house_clon.src.main.basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.config.BaseActivity
import com.example.today_s_house_clon.databinding.ActivityBasketBinding
import com.example.today_s_house_clon.src.main.basket.models.BasketResponse
import com.example.today_s_house_clon.src.main.store.StoreService

class BasketActivity :BaseActivity<ActivityBasketBinding>(ActivityBasketBinding::inflate), BasketInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jwt = ApplicationClass.sSharedPreferences.getString("jwt", null)
        showLoadingDialog(this)
        BasketService(this).tryGetBasket(jwt!!)

    }

    override fun onGetBasketSuccess(response: BasketResponse) {
        dismissLoadingDialog()
        val transaction = supportFragmentManager.beginTransaction()

        if (response.result.kartItemList.isEmpty()) {
            transaction.add(R.id.fl_container, EmptyBasketFragment())
            transaction.commit()
            binding.bottomAppBar.visibility = View.GONE
        }else {
            transaction.add(R.id.fl_container,BasketFragment())
            transaction.commit()
            binding.bottomAppBar.visibility = View.VISIBLE
        }
    }

    override fun onGetBasketFailure(message: String) {
        dismissLoadingDialog()
    }

}