package com.example.today_s_house_clon.src.main.home

import com.example.today_s_house_clon.src.main.home.models.HomeResponse
import com.example.today_s_house_clon.src.main.store.models.StoreResponse

interface InterestFragmentInterface {

    fun onGetHomeSuccess(response: HomeResponse)
    fun onGetHomeFailure(message: String)

    fun onGetStoreSuccess(response: StoreResponse)
    fun onGetStoreFailure(message: String)
}