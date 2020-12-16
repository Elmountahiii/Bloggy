package com.redgunner.bloggy.view.fragments


import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.redgunner.bloggy.R
import com.redgunner.bloggy.adapter.ArticleListAdapter
import com.redgunner.bloggy.models.Article
import com.redgunner.bloggy.models.Category
import com.redgunner.bloggy.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {


    private val viewModel: HomeViewModel by viewModels()

    private val articleListAdapter = ArticleListAdapter { articleId, markClick, state ->

        if (markClick) {
            viewModel.markArticle(articleId, state)
        } else {
            findNavController().navigate(
                HomeFragmentDirections.actionGlobalReadingFragment(
                    articleId
                )
            )
        }

    }


    override fun onStart() {
        super.onStart()
        setUpArticleList()

        viewModel.getArticles()
        viewModel.getRecommendedArticle()

        /*
        * displaying the article list when they are ready
        * */

        viewModel.articlesList.observe(viewLifecycleOwner, Observer { articleList ->
            if (articleList.isEmpty()){
                viewModel.getArticles()
            }else{
                articleListAdapter.submitList(articleList)

            }



        })

        /*
        * displaying the category list list when they are ready
        * */
        viewModel.categoryList.observe(viewLifecycleOwner, Observer { categorylist ->


            setUpCategoryList(categorylist)

        })


        viewModel.recommendedArticle.observe(viewLifecycleOwner, Observer { article ->
            if (article != null) {
                showSuggestArticle(article)
            }else{
                viewModel.getRecommendedArticle()
            }
        })



    }


    override fun onResume() {
        super.onResume()

        HomeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {


                    viewModel.getArticlesByCategory(tab?.text.toString())


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }


    private fun setUpArticleList() {
        HomeArticleList.apply {
            this.layoutManager =
                GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)

            this.adapter = articleListAdapter
        }
    }

    private fun setUpCategoryList(list: List<Category>) {


        if (list.isNotEmpty()) {
            HomeTabLayout.removeAllTabs()

            val tab = HomeTabLayout.newTab()
            tab.text = "All"
            HomeTabLayout.addTab(tab)

            for (category in list) {

                val tab = HomeTabLayout.newTab()
                tab.text = category.name
                HomeTabLayout.addTab(tab)


            }
        }


    }

    private fun showSuggestArticle(article: Article) {


        SuggestArticleTitle.text = article.title
        SuggestArticleCategory.text = article.category
        SuggestArticleBookMark.isChecked = article.articleSaved
        SuggestArticleCategory.setTextColor(Color.parseColor(article.categoryColor))
        Glide.with(this)
            .load(article.imageURL).into(SuggestArticleImage)

        SuggestArticleBookMark.setOnCheckedChangeListener { buttonView, isChecked ->

            viewModel.markArticle(article.id, isChecked)



        }

        SuggestArticleTitle.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionGlobalReadingFragment(
                    article.id
                )
            )
        }

        SuggestArticleImage.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionGlobalReadingFragment(
                    article.id
                )
            )
        }


    }


}





