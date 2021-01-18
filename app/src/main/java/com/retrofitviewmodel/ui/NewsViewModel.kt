package com.retrofitviewmodel.ui


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.retrofitviewmodel.network.Retrofit
import com.rxandroid_retrofit_recycleview_kotlin.data.Article
import com.rxandroid_retrofit_recycleview_kotlin.data.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsViewModel : ViewModel() {


    private var articles: MutableLiveData<List<Article>>? = null

    fun getHeroes(): MutableLiveData<List<Article>>? {
        //if the list is null
        if (articles == null) {
            articles = MutableLiveData()
            //we will load it asynchronously from server in this method
            loadNewsData()
        }

        //finally we will return the list
        return articles
    }

    private fun loadNewsData() {
        val call = Retrofit.api.getAllData("in", 1)
        call.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("onFailure ", t.toString())
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val res = response.body()
                if (res != null) {
                    println(response)
                    articles!!.value = res.articles

                }
            }
        })
    }
}


