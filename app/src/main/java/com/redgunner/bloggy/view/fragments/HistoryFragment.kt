package com.redgunner.bloggy.view.fragments

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.redgunner.bloggy.R
import com.redgunner.bloggy.adapter.ArticleListAdapter
import com.redgunner.bloggy.viewmodels.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.history_fragment.*

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.history_fragment) {


    private val viewModel: HistoryViewModel by viewModels()

    private val historyListAdapter = ArticleListAdapter { articleId, markClick, state ->

        if (markClick) {

            viewModel.markArticle(articleId,state)

        }else{
            findNavController().navigate(HistoryFragmentDirections.actionGlobalReadingFragment(articleId))
        }

    }


    override fun onStart() {
        super.onStart()
        setUpHistoryList()

        // displaying history Article when they are ready
        viewModel.historyList.observe(viewLifecycleOwner, Observer { historyList ->
            if (historyList.isNotEmpty()) {
                HistoryList.visibility = View.VISIBLE
                HistoryEmpty.visibility = View.INVISIBLE
                historyListAdapter.submitList(historyList)

            } else {
                HistoryList.visibility = View.INVISIBLE
                HistoryEmpty.visibility = View.VISIBLE
            }

        })
    }


    /*history recyclerView
    * */
    private fun setUpHistoryList() {
        HistoryList.apply {
            this.layoutManager =
                GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)

            this.adapter = historyListAdapter
        }
    }


}