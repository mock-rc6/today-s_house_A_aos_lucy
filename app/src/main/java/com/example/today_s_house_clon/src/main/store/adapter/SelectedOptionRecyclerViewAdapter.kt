package com.example.today_s_house_clon.src.main.store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.today_s_house_clon.databinding.ItemSelectedOptionBinding
import com.example.today_s_house_clon.src.main.store.models.ResultOption

class SelectedOptionRecyclerViewAdapter(): RecyclerView.Adapter<SelectedOptionRecyclerViewAdapter.OptionViewHolder>() {
    private lateinit var binding: ItemSelectedOptionBinding
    private var optionList = mutableListOf<ResultOption>()


    inner class OptionViewHolder(private val binding: ItemSelectedOptionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(option: ResultOption){
            binding.tvOptionTitle.text = "선택"+ adapterPosition.toString()+". ${option.optionName}"
            binding.tvPrice.text = option.saledPrice
        }
    }

    fun addSelectedOptionList(item: MutableList<ResultOption>, number: Int){
        optionList.add(item[number])
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        binding = ItemSelectedOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.bind(optionList[position])
    }

    override fun getItemCount(): Int = optionList.size
}