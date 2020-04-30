package com.tdd.uchit.moviehunt.data.repository

import android.annotation.SuppressLint
import com.tdd.uchit.moviehunt.data.db.MovieDao
import com.tdd.uchit.moviehunt.data.model.Data
import com.tdd.uchit.moviehunt.data.model.MovieResponse
import com.tdd.uchit.moviehunt.data.remote.MovieService
import io.reactivex.Maybe

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieDao: MovieDao, private val movieService: MovieService) :
    MovieRepository {

    override fun fetchMovies(): Maybe<MovieResponse> {
        return Maybe.concatArray(fetchMoviesFromDb(), fetchMoviesFromApi()).firstElement()
    }

    override fun fetchMoviesBYID(id: Int): Maybe<Data> {
        return movieService.fetchMoviesBYID(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Timber.d("Dispatching $it movies from API BY ID")
            }
    }

    private fun fetchMoviesFromApi(): Maybe<MovieResponse> {
        return movieService.fetchMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Timber.d("Dispatching ${it.data.size} movies from API")
                storeMoviesInDb(it)
            }
    }

    private fun fetchMoviesFromDb(): Maybe<MovieResponse> {
        return movieDao.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter {
                it.data.isNotEmpty()
            }
            .doOnSuccess {
                Timber.d("Dispatching ${it.data.size} movies from Database")
            }
    }

    @SuppressLint("CheckResult")
    private fun storeMoviesInDb(movies: MovieResponse) {
        Maybe.fromCallable { movieDao.insert(movies) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Timber.d("Inserted ${movies.data.size} movies from API in Database")
            }
    }
}