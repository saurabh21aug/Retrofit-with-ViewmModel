package com.retrofitviewmodel.network


import com.rxandroid_retrofit_recycleview_kotlin.data.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "82e76a4e6d4540909a9bb6eaca329868"
    }

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getAllData(@Query("country") country: String, @Query("page") page: Int): Call<News>

}
