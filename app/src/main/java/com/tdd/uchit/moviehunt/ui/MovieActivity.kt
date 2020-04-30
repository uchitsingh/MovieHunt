package com.tdd.uchit.moviehunt.ui

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tdd.uchit.moviehunt.App
import com.tdd.uchit.moviehunt.R
import com.tdd.uchit.moviehunt.data.repository.MovieRepository
import com.tdd.uchit.moviehunt.ui.adapter.MovieAdapter
import com.tdd.uchit.moviehunt.viewmodel.MovieViewModel
import com.tdd.uchit.moviehunt.viewmodel.factory.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_movie.*
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    companion object {
        const val MOVIE_ID = "movie_id"
    }

    @Inject
    lateinit var movieRepository: MovieRepository

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        (applicationContext as App).appComponent.inject(this)

        initRecyclerView()
        setViewModel()
        setSearchView()
    }

    private fun initRecyclerView() {
        movie_rv.layoutManager = GridLayoutManager(this, 3)
        adapter = MovieAdapter(object : MovieAdapter.OnItemClickListener {
            override fun onMovieClicked(id: Int) {
                val intent = Intent(this@MovieActivity, MovieDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(MOVIE_ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        movie_rv.adapter = adapter
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(
            this,
            MovieViewModelFactory(movieRepository)
        ).get(MovieViewModel::class.java)

        viewModel.fetchMovies()

        viewModel.moviesObservable.observe(this, Observer {
            adapter.setData(it)
        })

        viewModel.searchObservable.observe(this, Observer {
            adapter.setData(it)
        })
    }

    private fun setSearchView() {
        movie_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                 viewModel.search(newText)
                return true
            }
        })
    }

}
