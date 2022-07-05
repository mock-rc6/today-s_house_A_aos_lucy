package com.example.today_s_house_clon.src.main.advertisement

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemHomeAdvertisementBannerBinding
import com.example.today_s_house_clon.src.main.home.models.EventInfo

class HomeAdvertisementAdapter(): RecyclerView.Adapter<HomeAdvertisementAdapter.CustomViewHolder>() {

    private lateinit var binding: ItemHomeAdvertisementBannerBinding
    private var imageList = mutableListOf<EventInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CustomViewHolder {

        binding = ItemHomeAdvertisementBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding).apply {
            // 광고 클릭 리스너
            itemView.setOnClickListener {

            }
        }

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

    inner class CustomViewHolder(private val binding: ItemHomeAdvertisementBannerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: EventInfo) {

            Glide.with(binding.root).load(item.eventImgUrl).into(binding.ivBanner)
        }
    }
}