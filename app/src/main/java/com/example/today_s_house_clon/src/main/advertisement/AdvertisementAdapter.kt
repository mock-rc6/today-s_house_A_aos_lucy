package com.example.today_s_house_clon.src.main.advertisement

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.databinding.ItemAdvertisementBannerBinding
import com.example.today_s_house_clon.src.main.store.models.EventImg

class AdvertisementAdapter(): RecyclerView.Adapter<AdvertisementAdapter.CustomViewHolder>() {

    private lateinit var binding: ItemAdvertisementBannerBinding
    private var imageList = mutableListOf<EventImg>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CustomViewHolder {
        binding = ItemAdvertisementBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
    fun addImg(item: List<EventImg>) {
        imageList.addAll(item)
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(private val binding: ItemAdvertisementBannerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: EventImg) {

            Glide.with(binding.root).load(item.storeEventImgUrl).into(binding.ivBanner)
        }
    }
}