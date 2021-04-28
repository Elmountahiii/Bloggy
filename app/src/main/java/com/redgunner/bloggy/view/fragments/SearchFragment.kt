package com.redgunner.bloggy.view.fragments

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.redgunner.bloggy.R
import com.redgunner.bloggy.adapter.ArticleListAdapter
import com.redgunner.bloggy.utils.ArticleClickState
import com.redgunner.bloggy.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search_fragment.*

@AndroidEntryPoint

class SearchFragment : Fragment(R.layout.search_fragment){

    private val viewModel: SearchViewModel by viewModels()

    private val articleListAdapter = ArticleListAdapter{articleClickState ->

        when(articleClickState){
            is ArticleClickState.ReadClick ->{


                findNavController().navigate(SearchFragmentDirections.actionGlobalReadingFragment(articleClickState.ArticleId))





            }
            is ArticleClickState.MarkClick ->{
                viewModel.markArticle(articleClickState.ArticleId, articleClickState.CheckedState)
            }

        }

    }

    override fun onStart() {
        super.onStart()
        setUpSearchList()

        viewModel.searchList.observe(viewLifecycleOwner, Observer { searchlist ->

            if (searchlist.isEmpty()){
                Search_List.visibility=View.INVISIBLE
                NoResultsTxT.visibility= View.VISIBLE

                NoResultsTxT.text= "${resources.getText(R.string.NoResult)} ${Input.text.toString()} "

            }else{
                Search_List.visibility=View.VISIBLE
                NoResultsTxT.visibility= View.INVISIBLE

                articleListAdapter.submitList(searchlist)
            }

        })

    }

    override fun onResume() {
        super.onResume()
        Input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(count==0){
                    Search_List.visibility = View.INVISIBLE

                }
                else{
                    Search_List.visibility = View.INVISIBLE
                    viewModel.search(s.toString())
                }



            }
        })
    }

    private fun setUpSearchList() {

        Search_List.apply {
            this.layoutManager =
                GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)



            this.adapter = articleListAdapter

        }


    }



}