package com.tdd.uchit.moviehunt.data.repository

import com.tdd.uchit.moviehunt.data.model.Data
import com.tdd.uchit.moviehunt.data.model.MovieResponse
import io.reactivex.Maybe

interface MovieRepository {
    fun fetchMovies(): Maybe<MovieResponse>
    fun fetchMoviesBYID(id:Int): Maybe<Data>
}