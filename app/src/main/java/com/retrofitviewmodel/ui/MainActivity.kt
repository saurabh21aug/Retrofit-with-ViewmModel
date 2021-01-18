package com.retrofitviewmodel.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.retrofitviewmodel.R
import com.retrofitviewmodel.adapter.NewsAdapter

import com.rxandroid_retrofit_recycleview_kotlin.data.Article
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var adapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProvider(this).get(NewsViewModel::class.java)

        adapter = NewsAdapter(this@MainActivity, ArrayList())

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        val helper: SnapHelper = LinearSnapHelper()
        helper.attachToRecyclerView(recyclerView)

        model.getHeroes()?.observe(this, Observer { articles ->
            if (articles?.size!! > 0) {
                adapter?.setData(articles)
                recyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })
    }
}

