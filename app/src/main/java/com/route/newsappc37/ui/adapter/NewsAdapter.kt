package com.route.newsappc37.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.route.newsappc37.R
import com.route.newsappc37.model.ArticlesItem

class NewsAdapter(var articles: List<ArticlesItem?>? = null) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    fun updateData(articles: List<ArticlesItem?>?) {
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = articles?.get(position)
        holder.title.text = item?.title
        holder.author.text = item?.author
        holder.publishedAt.text = item?.publishedAt
        Glide.with(holder.itemView).load(item?.urlToImage).into(holder.image)
    }

    class NewsViewHolder(val itemView: View) : ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.news_image)
        val publishedAt: TextView = itemView.findViewById(R.id.published_at)
        val author: TextView = itemView.findViewById(R.id.author)


    }

}