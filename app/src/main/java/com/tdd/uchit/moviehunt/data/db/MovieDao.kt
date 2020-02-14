package com.tdd.uchit.moviehunt.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tdd.uchit.moviehunt.data.model.MovieResponse
import io.reactivex.Maybe

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): Maybe<MovieResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieResponse: MovieResponse)

}