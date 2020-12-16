package com.redgunner.bloggy.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.redgunner.bloggy.view.fragments.HistoryFragment
import com.redgunner.bloggy.view.fragments.ProfileFragment
import com.redgunner.bloggy.view.fragments.SavedFragment


/*
* profile Top TabLayout And ViewPager Adapter
* */
class SavedStateAdapter (fragmentActivity: ProfileFragment) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> SavedFragment()
            else -> HistoryFragment()
        }


    }


}