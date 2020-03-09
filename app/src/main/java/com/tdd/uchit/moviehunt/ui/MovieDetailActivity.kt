package com.tdd.uchit.moviehunt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.tdd.uchit.moviehunt.App
import com.tdd.uchit.moviehunt.R
import com.tdd.uchit.moviehunt.data.repository.MovieRepository
import com.tdd.uchit.moviehunt.ui.MovieActivity.Companion.MOVIE_ID
import com.tdd.uchit.moviehunt.viewmodel.MovieDetailViewModel
import com.tdd.uchit.moviehunt.viewmodel.factory.MovieDetailViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var movieRepository: MovieRepository

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        (applicationContext as App).appComponent.inject(this)

        val bundle = intent.extras
        val id: Int? = bundle?.getInt(MOVIE_ID)

        viewModel = ViewModelProvider(
            this,
            MovieDetailViewModelFactory(movieRepository)
        ).get(MovieDetailViewModel::class.java)

        if (id != null) {
            viewModel.fetchMoviesByID(id)
        }
        viewModel.moviesObservable.observe(this, Observer {
            detail_tv.setText(it.plot)
            Picasso.get().load(it.poster).into(detail_iv)
        })

    }
}
