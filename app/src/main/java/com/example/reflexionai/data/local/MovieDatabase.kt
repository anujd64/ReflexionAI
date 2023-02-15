package com.example.reflexionai.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reflexionai.data.local.dao.MovieDao
import com.example.reflexionai.model.Movie

@Database(entities = [Movie::class], version = 3)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

    companion object {

        private var INSTANCE: MovieDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MovieDatabase? {
            if (INSTANCE==null)
                INSTANCE =  Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movie_database").fallbackToDestructiveMigration().build()
            return INSTANCE!!
        }
    }

}