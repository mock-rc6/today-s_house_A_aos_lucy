package com.example.today_s_house_clon.src.main.recyclerViewAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemTodaysDealBinding
import com.example.today_s_house_clon.src.main.recyclerViewAdapter.TodaysDealRecyclerAdapter.DealViewHolder
import com.example.today_s_house_clon.src.main.store.models.TodayDeal

class TodaysDealRecyclerAdapter(): RecyclerView.Adapter<DealViewHolder>() {

    private lateinit var binding: ItemTodaysDealBinding
    private var dealList = mutableListOf<TodayDeal>()


    inner class DealViewHolder(private val binding: ItemTodaysDealBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(deal: TodayDeal) {
            // 모서리 둥글게
            binding.ivImg.clipToOutline = true

            Glide.with(binding.root).load(deal.hotDealThumbnail).into(binding.ivImg)
            binding.tvDue.text = deal.due
            binding.tvCompany.text = deal.companyName
            binding.tvItemName.text = deal.itemName
            binding.tvSaleRate.text = deal.saleRate
            binding.tvPrice.text = deal.price
            binding.tvScore.text = deal.score.toString()
            binding.tvReview.text = "리뷰 " + deal.reviewNum.toString()
        }

    }

    fun addImg(item: MutableList<TodayDeal>) {
        dealList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
        binding = ItemTodaysDealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DealViewHolder, position: Int) {
        holder.bind(dealList[position])

    }

    override fun getItemCount(): Int = dealList.size

}


