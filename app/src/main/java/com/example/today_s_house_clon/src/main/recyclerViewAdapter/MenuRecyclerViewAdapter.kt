package com.example.today_s_house_clon.src.main.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.today_s_house_clon.databinding.ItemInsideMenuBinding
import com.example.today_s_house_clon.src.main.recyclerViewAdapter.MenuRecyclerViewAdapter.MenuViewHolder

class MenuRecyclerViewAdapter(private val menuList: ArrayList<GridMenuVO>): RecyclerView.Adapter<MenuViewHolder>() {

    private lateinit var binding: ItemInsideMenuBinding

    inner class MenuViewHolder(menu: View):RecyclerView.ViewHolder(menu){
        val menuImg: ImageView = binding.imgBtn
        val menuTitle: TextView = binding.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        binding = ItemInsideMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        holder.menuImg.setImageResource(menu.image)
        holder.menuTitle.setText(menu.title)
    }

    override fun getItemCount(): Int = menuList.size

}