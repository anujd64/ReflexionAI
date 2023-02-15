package com.example.reflexionai.data.network

import com.example.reflexionai.model.MovieList
import retrofit2.http.GET

interface MovieApi {
    @GET("1.json")
    suspend fun getMovies1(): MovieList
    @GET("2.json")
    suspend fun getMovies2(): MovieList
}
