package com.tdd.uchit.moviehunt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}

