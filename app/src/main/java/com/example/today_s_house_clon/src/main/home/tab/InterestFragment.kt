package com.example.today_s_house_clon.src.main.home.tab

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.today_s_house_clon.R
import com.example.today_s_house_clon.config.BaseFragment
import com.example.today_s_house_clon.databinding.FragmentInterestBinding
import com.example.today_s_house_clon.src.main.recyclerViewAdapter.GridMenuVO
import com.example.today_s_house_clon.src.main.recyclerViewAdapter.MenuRecyclerViewAdapter


class InterestFragment : BaseFragment<FragmentInterestBinding>(FragmentInterestBinding::bind, R.layout.fragment_interest) {

    private var menuList = ArrayList<GridMenuVO>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 메뉴 리스트 추가
        addMenuList()
        // 상단 메뉴 어댑터 연결
        binding.rvHomeInterestFirst.adapter = MenuRecyclerViewAdapter(menuList)
        binding.rvHomeInterestFirst.layoutManager = GridLayoutManager(requireContext(),5)

    }

    // 메뉴 리스트
    private fun addMenuList() {

        menuList.clear()
        menuList.add(GridMenuVO(R.drawable.ic_interest_1, "쇼핑하기"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_2, "빠른배송"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_3, "N평집들이"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_4, "공간별사진"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_5, "리모델링"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_6, "쉬운이사"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_7, "오늘의딜"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_8, "누르면할인"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_9, "라스트세일"))
        menuList.add(GridMenuVO(R.drawable.ic_interest_10, "집에서뭐해?"))

    }
}