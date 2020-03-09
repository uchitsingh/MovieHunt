package com.tdd.uchit.moviehunt.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tdd.uchit.moviehunt.R
import com.tdd.uchit.moviehunt.data.model.Data
import com.tdd.uchit.moviehunt.data.model.MovieResponse
import com.tdd.uchit.moviehunt.ui.adapter.viewholder.MovieViewHolder

class MovieAdapter(private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {
    private val movies = mutableListOf<Data>()
    private val moviesCopy = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent
                , false
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(movies[position])
        holder.itemView.setOnClickListener {
            val id = movies[position].id
            if (id != null) {
                itemClickListener.onMovieClicked(id)
            }
        }
    }

    fun setData(movieResponse: MovieResponse) {
        movies.clear()
        movies.addAll(movieResponse.data)
        moviesCopy.clear()
        moviesCopy.addAll(movies)
        notifyDataSetChanged()
    }

    @SuppressLint("DefaultLocale")
    fun filter(text: String) {
        var searchWord = text
        movies.clear()
        if (searchWord.isEmpty()) {
            movies.addAll(moviesCopy)
        } else {
            searchWord = searchWord.toLowerCase()
            for (movie in moviesCopy) {
                if (movie.genre!!.toLowerCase().contains(searchWord) || movie.title!!.toLowerCase().contains(searchWord)) {
                    movies.add(movie)
                }
            }
        }
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onMovieClicked(id: Int)
    }

}