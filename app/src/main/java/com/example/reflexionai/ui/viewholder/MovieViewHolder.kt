package com.example.reflexionai.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reflexionai.R
import com.example.reflexionai.databinding.ItemMovieBinding
import com.example.reflexionai.model.Movie

class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie){


        val cast = "Cast: "+ movie.Cast?.replace("|", ", ")
        val genres =  movie.Genres?.replace("|", ", ")
        val runtime = movie.Runtime +"m"
        binding.title.text = movie.Title
        binding.year.text = movie.Year
        binding.runtime.text = runtime
        binding.rating.text = movie.Rating
        binding.shortSummary.text = movie.shortSummary
        binding.cast.text = cast
        binding.genres.text = genres
        Glide.with(itemView.context)
            .load(movie.moviePoster)
            .placeholder(R.color.lightGrey)
            .into(binding.poster)
    }
}