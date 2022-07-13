package com.example.today_s_house_clon.src.main.store

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.databinding.DialogBuyItemBinding
import com.example.today_s_house_clon.src.main.store.adapter.OptionRecyclerViewAdapter
import com.example.today_s_house_clon.src.main.store.adapter.SelectedOptionRecyclerViewAdapter
import com.example.today_s_house_clon.src.main.store.models.*
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener

class CustomDialog(val context: Context): StoreInterface {

    private lateinit var binding: DialogBuyItemBinding
    private val dialog = Dialog(context)
    private lateinit var onClickListener: ButtonClickListener
    private lateinit var optionAdapter: OptionRecyclerViewAdapter
    private lateinit var selectedAdapter: SelectedOptionRecyclerViewAdapter

    private var optionList = mutableListOf<ResultOption>()

    interface ButtonClickListener {
        fun onInMyBasketClicked(optionId: Long, number: Int)
        fun onBuyNowClicked(optionId: Long, number: Int)
        fun onTouchOption( rv: RecyclerView)
    }

    fun showDialog(){

        binding = DialogBuyItemBinding.inflate(LayoutInflater.from(context))

        val editor = ApplicationClass.sSharedPreferences
        val jwt = editor.getString("jwt", null)
        val userId = ApplicationClass.sSharedPreferences.getLong("userId", 0)
        val itemId = ApplicationClass.sSharedPreferences.getLong("itemId", 1.toLong())

        StoreService(this).tryGetItemOption(jwt!!, userId, itemId)

        dialog.setContentView(R.layout.dialog_buy_item)

        // dialog 크기
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        // dialog 위치
        dialog.window?.setGravity(Gravity.BOTTOM)

        // dialog 배경
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 바깥 터치 취소
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        // 상품 이미지 >> 이미지 저장 실패
        val image = ApplicationClass.sSharedPreferences.getString("image", null)

        binding.ivItem.clipToOutline = true
        Glide.with(context).load(image).into(binding.ivItem)

        val selectOption = binding.llSelectItem
        val option = binding.rvItemOption
        val choice = binding.rvSelectItem

        optionAdapter = OptionRecyclerViewAdapter()
        option.adapter = optionAdapter
        option.layoutManager = LinearLayoutManager(context)

        selectedAdapter = SelectedOptionRecyclerViewAdapter()
        choice.adapter = selectedAdapter
        choice.layoutManager = LinearLayoutManager(context)

        // 옵션 선택 리스너 open
        selectOption.setOnClickListener {
            onClickListener.onTouchOption(option)
        }


        // 옵션 선택 리사이클러뷰 클릭 리스너
        optionAdapter.setOnItemClickListener(object: OptionRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: ResultOption, position: Int) {
                if (optionList.size != null){
                    selectedAdapter.addSelectedOptionList(optionList, position)
                }
            }
        })

        // 리사이클러뷰 어댑터 연결 2개
        val inMyBasket = dialog.findViewById<AppCompatButton>(R.id.btn_in_my_basket)
        val buyNow = dialog.findViewById<AppCompatButton>(R.id.btn_buy_now)
        val optionId = 1
        val number = 1

        // 장바구니 담기 클릭리스너
        inMyBasket.setOnClickListener {
            onClickListener.onInMyBasketClicked(optionId.toLong(), number.toInt())
            dialog.dismiss()
        }

        // 바로구매 클릭리스너
        buyNow.setOnClickListener {
            onClickListener.onBuyNowClicked(optionId.toLong(), number.toInt())
            dialog.dismiss()
        }

        dialog.show()
    }

    fun setOnclickListener(listener: ButtonClickListener) {
        onClickListener = listener
    }

    override fun onGetItemOptionSuccess(response: SelectItemOptionResponse) {
        Log.d("TAG", "성공 : ${response.code}, ${response.result.size}")
        optionList = response.result.toMutableList()
        optionAdapter.addOptionList(optionList)


    }

    override fun onGetItemOptionFailure(message: String) {
        Log.d("TAG", "실패 : $message")

    }

    override fun onGetStoreSuccess(response: StoreResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetStoreFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetItemDetailSuccess(response: DetailResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetItemDetailFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPutInBasketSuccess(response: SelectItemResponse) {
        TODO("Not yet implemented")
    }

    override fun onPutInBasketFailure(message: String) {
        TODO("Not yet implemented")
    }



}