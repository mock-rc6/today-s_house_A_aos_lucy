package com.example.today_s_house_clon.src.main.store.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemSelectOptionBinding
import com.example.today_s_house_clon.src.main.store.models.ResultOption


class OptionRecyclerViewAdapter(): RecyclerView.Adapter<OptionRecyclerViewAdapter.OptionViewHolder>() {

    private lateinit var binding: ItemSelectOptionBinding
    private var optionList = mutableListOf<ResultOption>()
    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(v: View, data: ResultOption, position: Int)
    }

    inner class OptionViewHolder(private val binding: ItemSelectOptionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(option: ResultOption){
            binding.tvCount.text = position.toString()
            binding.ivOption.clipToOutline = true
            Glide.with(binding.root).load(option.thumbnail).into(binding.ivOption)
            binding.tvOptionTitle.text = option.optionName
            binding.tvOptionSaleRate.text = option.saleRate
            binding.tvOptionPrice.text = option.saledPrice
            if (option.specialPrice == "특가") {
                binding.tvHotSale.visibility = View.VISIBLE
            }
            if (option.delivery == "무료배송"){
                binding.tvFreeDelivery.visibility = View.VISIBLE
            }
        }
    }

    fun addOptionList(item: MutableList<ResultOption>){
        optionList.addAll(item)
        Log.d("TAG", " 리사이클러뷰 리스트 사이즈: ${optionList.size}")
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        binding = ItemSelectOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.bind(optionList[position])
    }

    override fun getItemCount(): Int = optionList.size
}