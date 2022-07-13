package com.example.today_s_house_clon.src.main.store

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.databinding.DialogBuyItemBinding
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener

class CustomDialog(val context: Context) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: ButtonClickListener

    interface ButtonClickListener {
        fun onInMyBasketClicked(optionId: Long, number: Int)
        fun onBuyNowClicked(optionId: Long, number: Int)
    }

    fun showDialog(){

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

        val inMyBasket = dialog.findViewById<AppCompatButton>(R.id.btn_in_my_basket)
        val buyNow = dialog.findViewById<AppCompatButton>(R.id.btn_buy_now)
        val optionId = 1
        val number = 1

        inMyBasket.setOnClickListener {
            onClickListener.onInMyBasketClicked(optionId.toLong(), number.toInt())
            dialog.dismiss()
        }

        buyNow.setOnClickListener {
            onClickListener.onBuyNowClicked(optionId.toLong(), number.toInt())
            dialog.dismiss()
        }

        dialog.show()
    }

    fun setOnclickListener(listener: ButtonClickListener) {
        onClickListener = listener
    }

}