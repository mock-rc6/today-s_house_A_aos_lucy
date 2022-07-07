package com.example.today_s_house_clon.src.main.store.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemDetailImageBinding
import com.example.today_s_house_clon.src.main.home.models.EventInfo
import com.example.today_s_house_clon.src.main.store.models.ResultItemDetail

class ItemDetailImagePagerAdapter(): RecyclerView.Adapter<ItemDetailImagePagerAdapter.CustomViewHolder>(){

    private lateinit var binding: ItemDetailImageBinding
    private var imageList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CustomViewHolder {
        binding = ItemDetailImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addImg(item: List<String>) {
        imageList.addAll(item)
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(private val binding: ItemDetailImageBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String) {

            Glide.with(binding.root).load(item).into(binding.ivImage)
        }
    }
}