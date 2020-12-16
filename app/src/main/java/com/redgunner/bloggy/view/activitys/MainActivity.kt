package com.redgunner.bloggy.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.redgunner.bloggy.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationController = Navigation.findNavController(
            this,
            R.id.NevHostFragment
        )
        setupBottomNavigationMenu(navigationController)

    }



    /*
    * setUp navigation
    * */
    private fun setupBottomNavigationMenu(navController: NavController) {
        bottom_navigation?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }
}