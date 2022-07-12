package com.example.today_s_house_clon.src.main.store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemDetailFullImageBinding

class ItemDetailViewRecyclerViewAdapter(): RecyclerView.Adapter<ItemDetailViewRecyclerViewAdapter.ItemViewHolder>() {

    private lateinit var binding: ItemDetailFullImageBinding
    private var imageList = mutableListOf<String>()

    inner class ItemViewHolder(private val binding: ItemDetailFullImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String){
            Glide.with(binding.root).load(item).into(binding.image)
        }
    }

    fun addImage(item: List<String>){
        imageList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding = ItemDetailFullImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}