package com.route.newsappc37.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.entities.NewsItemDTO
import com.route.newsappc37.R
import com.route.newsappc37.databinding.NewsItemBinding


class NewsAdapter(var articles: List<NewsItemDTO ?>? = null) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var OnArticleClickListener2: OnArticleClickListener? = null

    fun updateData(articles: List<NewsItemDTO?>?) {
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        val itemBinding: NewsItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.news_item,
                parent,
                false
            )
        return NewsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = articles?.get(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            OnArticleClickListener2?.onArticleClick(item)
        }


    }

    class NewsViewHolder(val itemBinding: NewsItemBinding) : ViewHolder(itemBinding.root) {
        fun bind(articlesItem: NewsItemDTO?) {
            itemBinding.articleItem = articlesItem

        }
    }

}

interface OnArticleClickListener {

    fun onArticleClick(articleItem: NewsItemDTO?)
}