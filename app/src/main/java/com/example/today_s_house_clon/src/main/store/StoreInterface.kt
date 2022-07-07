package com.example.today_s_house_clon.src.main.store

import com.example.today_s_house_clon.src.main.store.models.DetailResponse
import com.example.today_s_house_clon.src.main.store.models.StoreResponse

interface StoreInterface {

    fun onGetStoreSuccess(response: StoreResponse)
    fun onGetStoreFailure(message: String)
    fun onGetItemDetailSuccess(response: DetailResponse)
    fun onGetItemDetailFailure(message: String)

}