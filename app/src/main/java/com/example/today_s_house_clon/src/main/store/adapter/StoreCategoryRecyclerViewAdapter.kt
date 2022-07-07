package com.example.today_s_house_clon.src.main.store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.today_s_house_clon.databinding.ItemStoreCategoryBinding
import com.example.today_s_house_clon.src.main.recyclerView.CategoryVO

class StoreCategoryRecyclerViewAdapter(private val categoryList: ArrayList<CategoryVO>): RecyclerView.Adapter<StoreCategoryRecyclerViewAdapter.CategoryViewHolder>() {

    private lateinit var binding: ItemStoreCategoryBinding

    inner class CategoryViewHolder(menu: View): RecyclerView.ViewHolder(menu){
        val categoryImg: ImageView = binding.ivProduct
        val categoryTitle: TextView = binding.tvTitle
        val categoryContent: TextView = binding.tvContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding = ItemStoreCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.categoryImg.setImageResource(category.img)
        holder.categoryTitle.text = category.title
        holder.categoryContent.text = category.content
    }

    override fun getItemCount(): Int = categoryList.size

}
