package com.example.today_s_house_clon.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemHomeInterestDealItemBinding
import com.example.today_s_house_clon.src.main.store.models.TodayDeal

class HomeTodayDealAdapter(): RecyclerView.Adapter<HomeTodayDealAdapter.HomeTodayDealViewHolder>() {

    private lateinit var binding: ItemHomeInterestDealItemBinding
    private var dealList = mutableListOf<TodayDeal>()

    inner class HomeTodayDealViewHolder(private val binding: ItemHomeInterestDealItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(deal: TodayDeal) {

            binding.ivHomeInterestTodayDeal.clipToOutline = true
            Glide.with(binding.root).load(deal.hotDealThumbnail).into(binding.ivHomeInterestTodayDeal)
            binding.tvHomeInterestDealItemTitle.text = deal.itemName
            binding.tvHomeInterestDealItemSale.text = deal.saleRate
            binding.tvHomeInterestTodayDealDue.text = deal.due
        }
    }

    fun addImg(item: MutableList<TodayDeal>) {
        dealList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTodayDealViewHolder {
        binding = ItemHomeInterestDealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeTodayDealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeTodayDealViewHolder, position: Int) {
        // 오늘의 딜 한개만 표출
        holder.bind(dealList[position])
    }

    override fun getItemCount(): Int = 1
}

