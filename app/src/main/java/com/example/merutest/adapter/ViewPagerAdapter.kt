package com.example.merutest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.merutest.fragment.ChickenFragment
import com.example.merutest.fragment.FishFragment
import com.example.merutest.fragment.PotatoFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0->{
                PotatoFragment()
            }
            1->{
                ChickenFragment()
            }
            2->{
                FishFragment()
            }
            else->{
                Fragment()
            }
        }
    }
}