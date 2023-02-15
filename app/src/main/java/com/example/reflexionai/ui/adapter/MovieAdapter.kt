package com.example.reflexionai.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.reflexionai.databinding.ActivityMainBinding
import com.example.reflexionai.databinding.ItemMovieBinding
import com.example.reflexionai.model.Movie
import com.example.reflexionai.model.MovieList
import com.example.reflexionai.ui.viewholder.MovieViewHolder

class MovieAdapter: ListAdapter<Movie, MovieViewHolder>(Diff_Util) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    companion object {
        private val Diff_Util = object : DiffUtil.ItemCallback<Movie>(){
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.IMDB_ID == newItem.IMDB_ID

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
        }
    }
}