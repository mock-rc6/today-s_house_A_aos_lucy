package com.example.today_s_house_clon.src.main.myPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.today_s_house_clon.databinding.ItemMyPagePhotoBinding
import com.example.today_s_house_clon.src.main.recyclerView.GridMenuVO

class MyPageAddPhotoRecyclerViewAdapter(private val photoList: ArrayList<GridMenuVO>): RecyclerView.Adapter<MyPageAddPhotoRecyclerViewAdapter.PhotoViewHolder>() {

    private lateinit var binding: ItemMyPagePhotoBinding

    inner class PhotoViewHolder(private val binding: ItemMyPagePhotoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(photo: GridMenuVO){
            binding.ivPhoto.setImageResource(photo.image)
            binding.tvTitle.text = photo.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        binding = ItemMyPagePhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int = photoList.size
}