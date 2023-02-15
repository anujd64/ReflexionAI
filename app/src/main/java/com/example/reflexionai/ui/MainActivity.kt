package com.example.reflexionai.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import androidx.room.Room
import com.example.reflexionai.data.local.MovieDatabase
import com.example.reflexionai.data.repository.MovieRepository
import com.example.reflexionai.databinding.ActivityMainBinding
import com.example.reflexionai.model.MovieList
import com.example.reflexionai.ui.adapter.MovieAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel
    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recyclerView = binding.rvMovies
        recyclerView.layoutManager =LinearLayoutManager(this, HORIZONTAL ,false)
        recyclerView.hasFixedSize()
        recyclerView.adapter = adapter

        var movieList: MovieList
        val instance = MovieDatabase.getInstance(this)

        val movieRepository =MovieRepository(instance!!.getMovieDao())
        viewModel = MainViewModel(movieRepository)
        getData()

        lifecycleScope.launch(Dispatchers.IO){
            viewModel.movies
                .collect{
                    movieList = it
                    adapter.submitList(movieList.movies)
                }
        }
    }

    private fun getData(){
        if(isConnected()){
            viewModel.getMovies1()
            binding.refresh.setOnClickListener {
                viewModel.getMovies2()
            }
            Toast.makeText(this,"Internet available, fetching from API",Toast.LENGTH_LONG).show()
        }else{
            viewModel.getMoviesFromLocal()
            Toast.makeText(this,"Internet unavailable, fetching from database",Toast.LENGTH_LONG).show()
        }
    }

    private fun isConnected(): Boolean{
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}


