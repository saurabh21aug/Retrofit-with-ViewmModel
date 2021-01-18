package com.retrofitviewmodel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.retrofitviewmodel.R
import com.rxandroid_retrofit_recycleview_kotlin.data.Article
import kotlinx.android.synthetic.main.item_layout.view.*


class NewsAdapter(private final var contect: Context, private var articles: List<Article?>?) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = articles!!.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles?.get(position)

        holder.title.text = article!!.title
        holder.description.text = article.description

        Glide.with(contect).load(article.urlToImage).into(holder.image)

    }

    fun setData(articleList: List<Article?>?) {
        this.articles = articleList
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val description = itemView.description
        val image = itemView.image
    }
}