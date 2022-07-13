package com.example.today_s_house_clon.src.main.store

import com.example.today_s_house_clon.src.main.store.models.*

interface StoreInterface {

    fun onGetStoreSuccess(response: StoreResponse)
    fun onGetStoreFailure(message: String)
    fun onGetItemDetailSuccess(response: DetailResponse)
    fun onGetItemDetailFailure(message: String)
    fun onPutInBasketSuccess(response: SelectItemResponse)
    fun onPutInBasketFailure(message: String)
    fun onGetItemOptionSuccess(response: SelectItemOptionResponse)
    fun onGetItemOptionFailure(message: String)

}