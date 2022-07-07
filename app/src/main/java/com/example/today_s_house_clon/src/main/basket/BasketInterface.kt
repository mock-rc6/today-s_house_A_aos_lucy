package com.example.today_s_house_clon.src.main.basket

import com.example.today_s_house_clon.src.main.basket.models.BasketResponse
import com.example.today_s_house_clon.src.main.store.models.StoreResponse

interface BasketInterface {

    fun onGetBasketSuccess(response: BasketResponse)
    fun onGetBasketFailure(message: String)
}