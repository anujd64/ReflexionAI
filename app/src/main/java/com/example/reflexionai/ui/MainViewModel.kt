package com.example.reflexionai.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reflexionai.data.local.dao.MovieDao
import com.example.reflexionai.data.repository.MovieRepository
import com.example.reflexionai.model.MovieList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies: MutableStateFlow<MovieList> = MutableStateFlow(MovieList())

    val movies: StateFlow<MovieList> = _movies

    fun getMoviesFromLocal() =
        viewModelScope.launch {
            movieRepository.getMoviesFromLocal()
                .onEach {
                    Log.d("getMoviesFromLocal", it.toString())
                }
                .collect{
                    _movies.value = MovieList(it)
                }
        }


    fun getMovies1() =
        viewModelScope.launch{
            movieRepository.getMovieList1()
                .collect{
                        movieList -> _movies.value = movieList
                }
        }

    fun getMovies2() =
        viewModelScope.launch {
            movieRepository.getMovieList2()
                .collect { movieList -> _movies.value = movieList
                }
        }
}