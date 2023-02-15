package com.example.reflexionai.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("Movie List")
    var movies : List<Movie> = listOf<Movie>()
)