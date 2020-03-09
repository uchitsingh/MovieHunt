package com.tdd.uchit.moviehunt.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tdd.uchit.moviehunt.data.repository.MovieRepository
import com.tdd.uchit.moviehunt.viewmodel.MovieDetailViewModel

@Suppress("UNCHECKED_CAST")
class MovieDetailViewModelFactory(val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(repository) as T
    }
}