package com.example.reflexionai.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    val Cast: String?,
    val Director: String?,
    val Genres: String?,
    @SerializedName("IMDBID")
    @PrimaryKey
    val IMDB_ID: String,
    @SerializedName("Movie Poster")
    val moviePoster: String?,
    val Rating: String?,
    val Runtime: String?,
    @SerializedName("Short Summary")
    val shortSummary: String?,
    val Summary: String?,
    val Title: String?,
    val Writers: String?,
    val Year: String?,
    @SerializedName("YouTube Trailer")
    val youTubeTrailer: String?
)