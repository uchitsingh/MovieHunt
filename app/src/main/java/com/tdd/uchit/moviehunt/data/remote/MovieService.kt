package com.tdd.uchit.moviehunt.data.remote

import com.tdd.uchit.moviehunt.data.model.Data
import com.tdd.uchit.moviehunt.data.model.MovieResponse
import com.tdd.uchit.moviehunt.utils.Constant
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET(Constant.MOVIE_URL)
    fun fetchMovies(): Maybe<MovieResponse>

    @GET(Constant.MOVIE_URL_BY_ID)
    fun fetchMoviesBYID(@Path( "id") id:Int): Maybe<Data>
}