package com.tdd.uchit.moviehunt.data.remote

import com.tdd.uchit.moviehunt.data.model.MovieResponse
import com.tdd.uchit.moviehunt.utils.Constant
import io.reactivex.Maybe
import retrofit2.http.GET

interface MovieService {
    @GET(Constant.MOVIE_URL)
    fun fetchMovies(): Maybe<MovieResponse>
}