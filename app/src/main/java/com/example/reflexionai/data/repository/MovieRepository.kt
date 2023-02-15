package com.example.reflexionai.data.repository

import android.util.Log
import com.example.reflexionai.data.local.MovieDatabase
import com.example.reflexionai.data.local.dao.MovieDao
import com.example.reflexionai.model.MovieList
import com.example.reflexionai.data.network.ApiService
import com.example.reflexionai.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepository(private val movieDao: MovieDao)

{
    fun getMoviesFromLocal(): Flow<List<Movie>> = movieDao.getAllMovies()

    fun getMovieList1(): Flow<MovieList> = flow {
        val response = ApiService
            .getApi()
            .getMovies1()
        saveToLocal(response)
        emit(response)
    }.flowOn(Dispatchers.IO)

    private suspend fun saveToLocal(response: MovieList) {
        movieDao.addMovies(response.movies)
    }

    fun  getMovieList2(): Flow<MovieList> = flow {
        val response = ApiService
            .getApi()
            .getMovies2()

        saveToLocal(response)
        emit(response)
    }.flowOn(Dispatchers.IO)

}