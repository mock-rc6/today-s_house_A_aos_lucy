package com.example.today_s_house_clon.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemHomeInterestRankingBinding
import com.example.today_s_house_clon.src.main.home.models.MainHouseInfo

class HomeRankingRecyclerViewAdapter(): RecyclerView.Adapter<HomeRankingRecyclerViewAdapter.RankingViewHolder>() {

    private lateinit var binding: ItemHomeInterestRankingBinding
    private var imageList = mutableListOf<MainHouseInfo>()

    inner class RankingViewHolder(private val binding: ItemHomeInterestRankingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(house: MainHouseInfo){
            // 모서리 둥글게
            var index = imageList.indexOf(house) + 1
            binding.ivContent.clipToOutline = true
            Glide.with(binding.root).load(house.houseImgList).into(binding.ivContent)
            binding.tvRanking.text = index.toString()
        }
    }

    fun addImg(item: MutableList<MainHouseInfo>) {
        imageList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        binding = ItemHomeInterestRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}