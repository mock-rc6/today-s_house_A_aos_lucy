package com.example.today_s_house_clon.src.main.store

import com.example.today_s_house_clon.src.main.store.models.StoreResponse

interface StoreFragmentInterface {

    fun onGetStoreSuccess(response: StoreResponse)
    fun onGetStoreFailure(message: String)

}