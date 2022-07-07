package com.example.today_s_house_clon.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.databinding.ItemHomeInterestHorizontalBinding
import com.example.today_s_house_clon.src.main.home.adapter.HomeCategoryRecyclerViewAdapter.CategoryViewHolder
import com.example.today_s_house_clon.src.main.recyclerView.CategoryVO

class HomeCategoryRecyclerViewAdapter(private val categoryList: ArrayList<CategoryVO>): RecyclerView.Adapter<CategoryViewHolder>() {

    private lateinit var binding: ItemHomeInterestHorizontalBinding

    inner class CategoryViewHolder(private val binding: ItemHomeInterestHorizontalBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(category: CategoryVO) {
            Glide.with(binding.root).load(category.img).into(binding.ivProduct)
            binding.tvProduct.text = category.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding = ItemHomeInterestHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size

}
