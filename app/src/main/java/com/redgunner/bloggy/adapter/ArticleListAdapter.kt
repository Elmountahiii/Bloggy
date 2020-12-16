package com.redgunner.bloggy.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redgunner.bloggy.R
import com.redgunner.bloggy.models.Article
import kotlinx.android.synthetic.main.article_holder_layout.view.*


/*
* RecyclerView Adapter
* */
class ArticleListAdapter(val articleClick: (articleId: Int, marlClick: Boolean, state: Boolean) -> Unit) :
    ListAdapter<Article, ArticleListAdapter.ArticleViewHolder>(ArticleComparator()) {


    inner class ArticleViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {

        private val image = itemView.ArticleImage
        private val title = itemView.ArticleTitle
        private val category = itemView.ArticleCategory
        private val time = itemView.ArticleTime
        private val bookMark = itemView.ArticleBookMark


        fun bind(article: Article) {

            Glide.with(context)
                .load(article.imageURL).into(image)

            title.text = article.title
            category.text = article.category
            bookMark.isChecked = article.articleSaved
            time.text = article.date
            category.setTextColor(Color.parseColor(article.categoryColor))

            image.setOnClickListener {
                articleClick(getItem(adapterPosition).id, false, false)

            }

            title.setOnClickListener {
                articleClick(getItem(adapterPosition).id, false, false)
            }

            bookMark.setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("zbi", "ana botona chiwahad clickni")
                articleClick(getItem(adapterPosition).id, true, isChecked)
            }

        }


    }

    class ArticleComparator() : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {


            return oldItem.articleSaved == newItem.articleSaved
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_holder_layout, parent, false)

        return ArticleViewHolder(view, parent.context)

    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


}