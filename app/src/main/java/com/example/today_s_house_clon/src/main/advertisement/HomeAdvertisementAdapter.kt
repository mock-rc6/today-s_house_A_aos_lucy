package com.example.today_s_house_clon.src.main.advertisement

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemHomeAdvertisementBannerBinding
import com.example.today_s_house_clon.src.main.home.EventActivity
import com.example.today_s_house_clon.src.main.home.models.EventInfo
import com.example.today_s_house_clon.src.main.store.adapter.TodaysDealRecyclerAdapter
import com.example.today_s_house_clon.src.main.store.models.TodayDeal

class HomeAdvertisementAdapter(): RecyclerView.Adapter<HomeAdvertisementAdapter.CustomViewHolder>(){

    private lateinit var binding: ItemHomeAdvertisementBannerBinding
    private var imageList = mutableListOf<EventInfo>()

    private lateinit var listener: HomeAdvertisementAdapter.OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(v: View, data: EventInfo, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        binding = ItemHomeAdvertisementBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val pageLength = imageList.size
        if (pageLength != 0) {
            holder.bind(imageList[position%pageLength])
        }
    }

    // 뷰페이저 전환이 무한처럼 보이도록 아이템 수 max
    override fun getItemCount(): Int = Int.MAX_VALUE

    @SuppressLint("NotifyDataSetChanged")
    fun addImg(item: List<EventInfo>) {
        imageList.addAll(item)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: HomeAdvertisementAdapter.OnItemClickListener) {
        this.listener = listener
    }

    inner class CustomViewHolder(private val binding: ItemHomeAdvertisementBannerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: EventInfo) {

            Glide.with(binding.root).load(item.eventImgUrl).into(binding.ivBanner)

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, item, pos)
                }
            }
        }
    }
}