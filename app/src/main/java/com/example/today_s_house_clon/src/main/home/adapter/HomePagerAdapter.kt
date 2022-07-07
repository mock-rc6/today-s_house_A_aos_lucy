package com.example.today_s_house_clon.src.main.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){
    var fragments : ArrayList<Fragment> = ArrayList()
    var tabList : ArrayList<String> = ArrayList()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun addFragment(fragment: Fragment, tab: String) {
        fragments.add(fragment)
        tabList.add(tab)
        notifyItemInserted(fragments.size - 1)
    }

}