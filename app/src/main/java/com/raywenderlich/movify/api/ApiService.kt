package com.raywenderlich.movify.api

import com.raywenderlich.movify.response.MovieListResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated")
    fun getPopularMovie(@Query("page") page: Int): Call<MovieListResponse>

}