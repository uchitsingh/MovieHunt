package com.tdd.uchit.moviehunt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tdd.uchit.moviehunt.data.model.Data
import com.tdd.uchit.moviehunt.data.model.MovieResponse
import com.tdd.uchit.moviehunt.data.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private var _moviesObservable: MutableLiveData<Data> = MutableLiveData()
    val moviesObservable: LiveData<Data>
        get() = _moviesObservable

    fun fetchMoviesByID(id: Int) {
        Timber.d("Fetch Movies By ID")
        disposable.add(repository.fetchMoviesBYID(id)
            .subscribe(
                {
                    Timber.d("Fetch Movies BY ID success")
                    _moviesObservable.value = it
                },
                {
                    Timber.e("Fetch Movies BY ID error%s", it.message)
                }
            )
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}

