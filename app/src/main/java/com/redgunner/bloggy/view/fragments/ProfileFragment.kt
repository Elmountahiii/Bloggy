package com.redgunner.bloggy.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.redgunner.bloggy.R
import com.redgunner.bloggy.adapter.SavedStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.profile_fragment.*

@AndroidEntryPoint

class ProfileFragment : Fragment(R.layout.profile_fragment) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpTabLayout()
    }



    private fun setUpTabLayout() {
        ProfileViewPager.adapter = SavedStateAdapter(this)

        TabLayoutMediator(ProfileTabLayout, ProfileViewPager) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = resources.getText(R.string.saved)
                }
                1 -> {
                    tab.text = resources.getText(R.string.history)
                }
            }

        }.attach()


    }



}