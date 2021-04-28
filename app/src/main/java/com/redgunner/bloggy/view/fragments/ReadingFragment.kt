package com.redgunner.bloggy.view.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.redgunner.bloggy.R
import com.redgunner.bloggy.adapter.ArticleListAdapter
import com.redgunner.bloggy.models.Article
import com.redgunner.bloggy.utils.ArticleClickState
import com.redgunner.bloggy.viewmodels.ReadingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.reading_fragment.*

@AndroidEntryPoint
class ReadingFragment : Fragment(R.layout.reading_fragment) {

    private val viewModel: ReadingViewModel by viewModels()

    private val similarListAdapter = ArticleListAdapter {articleClickState ->

        when(articleClickState){
            is ArticleClickState.ReadClick ->{
                findNavController().navigate(
                    ReadingFragmentDirections.actionGlobalReadingFragment(
                        articleClickState.ArticleId
                    )
                )




            }
            is ArticleClickState.MarkClick ->{
                viewModel.markArticle(articleClickState.ArticleId, articleClickState.CheckedState)
            }

        }

    }

    private val saveArgs: ReadingFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getArticle(saveArgs.articleId)
    }

    override fun onStart() {
        super.onStart()

        setUpSimilarArticleList()


        viewModel.article.observe(viewLifecycleOwner, Observer { article ->
            showArticle(article)
            viewModel.getSimilarArticles(article.category)
            viewModel.addArticleToHistory(article.id)


        })

        viewModel.similarArticles.observe(viewLifecycleOwner, Observer { similarArticles ->

            similarListAdapter.submitList(similarArticles)
        })
    }

    private fun setUpSimilarArticleList() {
        similarArticlesList.apply {

            this.adapter = similarListAdapter
            this.layoutManager =
                GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)



        }

    }

    private fun showArticle(article: Article) {

        ReadTitle.text = article.title
        ReadBody.text = article.body
        ReadCategory.text = article.category
        ReadBookMark.isChecked = article.articleSaved
        ReadCategory.setTextColor(Color.parseColor(article.categoryColor))
        Glide.with(this)
            .load(article.imageURL).fitCenter().into(ReadArticleImage)

        ReadBookMark.setOnCheckedChangeListener { buttonView, isChecked ->

            viewModel.markArticle(saveArgs.articleId, isChecked)
        }
    }


}