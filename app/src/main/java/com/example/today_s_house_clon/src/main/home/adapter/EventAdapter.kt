package com.example.today_s_house_clon.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemEventBannerBinding
import com.example.today_s_house_clon.src.main.home.models.ResultEvent

class EventAdapter(): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var eventList = mutableListOf<ResultEvent>()
    private lateinit var binding: ItemEventBannerBinding

    inner  class EventViewHolder(private val binding: ItemEventBannerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(event: ResultEvent){
            Glide.with(binding.root).load(event.bannerPic).into(binding.ivBanner)
            binding.tvDue.text = event.due
        }

    }

    fun addList(item: MutableList<ResultEvent>){
        eventList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        binding = ItemEventBannerBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int = eventList.size
}