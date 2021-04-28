package com.redgunner.bloggy.view.fragments

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.redgunner.bloggy.R
import com.redgunner.bloggy.adapter.ArticleListAdapter
import com.redgunner.bloggy.utils.ArticleClickState
import com.redgunner.bloggy.viewmodels.SavedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.saved_fragment.*

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.saved_fragment) {

    private val viewModel:SavedViewModel by viewModels ()

    private val savedListAdapter=ArticleListAdapter{articleClickState ->

        when(articleClickState){
            is ArticleClickState.ReadClick ->{


                findNavController().navigate(SavedFragmentDirections.actionGlobalReadingFragment( articleClickState.ArticleId))



            }
            is ArticleClickState.MarkClick ->{
                viewModel.markArticle(articleClickState.ArticleId, articleClickState.CheckedState)
            }

        }

    }


    override fun onStart() {
        super.onStart()
        setUpSavedList()
        viewModel.savedList.observe(viewLifecycleOwner, Observer { savedList ->

            if (savedList.isNotEmpty()){
                SavedList.visibility=View.VISIBLE
                savedEmpty.visibility=View.INVISIBLE

                savedListAdapter.submitList(savedList)

            }else{
                SavedList.visibility=View.INVISIBLE
                savedEmpty.visibility=View.VISIBLE
            }

        })
    }


    private fun setUpSavedList(){
        SavedList.apply {

            this.layoutManager =
                GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)

            this.adapter=savedListAdapter
        }
    }







}