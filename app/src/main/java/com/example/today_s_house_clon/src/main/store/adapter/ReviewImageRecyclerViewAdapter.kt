package com.example.today_s_house_clon.src.main.store.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today_s_house_clon.config.ApplicationClass
import com.example.today_s_house_clon.databinding.ItemReviewStylingBinding
import com.example.today_s_house_clon.src.main.store.models.ReviewList

class ReviewImageRecyclerViewAdapter(): RecyclerView.Adapter<ReviewImageRecyclerViewAdapter.ReviewViewHolder>() {

    private lateinit var binding: ItemReviewStylingBinding
    private var reviewImgList = arrayListOf<String>() // 리뷰리스트 중 이미지만 있는 리스트
    private var imageCnt = 0

    inner class ReviewViewHolder(private val binding: ItemReviewStylingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(img: String){
            binding.image.clipToOutline = true
            Glide.with(binding.root).load(img).into(binding.image)
        }
    }

    // 리뷰 리스트 추가하는 메서드
    @SuppressLint("NotifyDataSetChanged", "CommitPrefEdits")
    fun addReviewImageList(items: MutableList<ReviewList>){

        for (item in items){
            val img = item.imgList
            if(img != null) {
                imageCnt + img.size
                for (image in img ){
                    reviewImgList.add(image)
                }
            }
        }
        ApplicationClass.sSharedPreferences.edit().putString("imgCnt", imageCnt.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        binding = ItemReviewStylingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewImgList[position])
    }

    override fun getItemCount(): Int = reviewImgList.size
}