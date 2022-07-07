package com.example.today_s_house_clon.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemHomeInterestViewerBinding
import com.example.today_s_house_clon.src.main.home.models.MainHouseInfo

class MainHouseInfoRecyclerAdapter() : RecyclerView.Adapter<MainHouseInfoRecyclerAdapter.HouseInfoViewHolder>() {

    private lateinit var binding: ItemHomeInterestViewerBinding
    private var houseList = mutableListOf<MainHouseInfo>()

    inner class HouseInfoViewHolder(private val binding: ItemHomeInterestViewerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(house: MainHouseInfo){
            // 모서리 둥글게
            binding.ivHomeInterestViewer1.clipToOutline = true
            Glide.with(binding.root).load(house.houseImgList).into(binding.ivHomeInterestViewer1)
            binding.tvHomeInterestViewer1.text = house.houseDescriptionList
        }
    }

    fun addImg(item: MutableList<MainHouseInfo>) {
        houseList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseInfoViewHolder {

        binding = ItemHomeInterestViewerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  HouseInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseInfoViewHolder, position: Int) {
        holder.bind(houseList[position])
    }

    override fun getItemCount(): Int = houseList.size
}