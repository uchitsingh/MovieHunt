package com.tdd.uchit.moviehunt.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tdd.uchit.moviehunt.data.model.Data
import com.tdd.uchit.moviehunt.data.model.MovieResponse
import com.tdd.uchit.moviehunt.data.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private var _moviesObservable: MutableLiveData<MovieResponse> = MutableLiveData()
    val moviesObservable: LiveData<MovieResponse>
        get() = _moviesObservable

    private var _searchObservable: MutableLiveData<MovieResponse> = MutableLiveData()
    val searchObservable: LiveData<MovieResponse>
        get() = _searchObservable


    fun fetchMovies() {
        Timber.d("Fetch Movies")
        disposable.add(repository.fetchMovies()
            .subscribe(
                {
                    Timber.d("Fetch Movies success")
                    _moviesObservable.value = it
                },
                {
                    Timber.e("Fetch Movies error")
                }
            )
        )
    }

    @SuppressLint("DefaultLocale")
    fun search(text: String) {
        var searchWord = text
        val movies = _moviesObservable.value!!.data
        val moviesCopy = mutableListOf<Data>()

        if (searchWord.isBlank()) {
            moviesCopy.addAll(movies)
        } else {
            searchWord = searchWord.toLowerCase()

            movies.forEach { movie ->
                if (movie.genre!!.toLowerCase().contains(searchWord) || movie.title!!.toLowerCase().contains(
                        searchWord
                    )
                ) {
                    moviesCopy.add(movie)
                }
            }
        }
        _searchObservable.value = MovieResponse(moviesCopy)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}

