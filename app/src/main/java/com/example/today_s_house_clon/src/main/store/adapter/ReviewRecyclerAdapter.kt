package com.example.today_s_house_clon.src.main.store.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.today_s_house_clon.databinding.ItemReviewBinding
import com.example.today_s_house_clon.src.main.store.models.ReviewList
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReviewRecyclerAdapter(): RecyclerView.Adapter<ReviewRecyclerAdapter.ReviewViewHolder>() {

    private lateinit var binding: ItemReviewBinding
    private var reviewList = mutableListOf<ReviewList>()

    inner class ReviewViewHolder(private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(review: ReviewList) {
            binding.userId.text = review.userName
            binding.totalStar.rating = review.score.toFloat()
            val sdf = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            val date = LocalDate.parse(review.createdAt, DateTimeFormatter.ISO_DATE)
            binding.buyInfo.text = date.format(sdf) + " · " + review.buyAt
            binding.reviewContent.text = review.description

        }
    }

    fun addReview(item: MutableList<ReviewList>) {
        reviewList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewList[position])
    }

    override fun getItemCount(): Int {
        if(reviewList.size > 3) {
            return 3
        }else{
            return reviewList.size
        }
    }
}