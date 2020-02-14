package com.tdd.uchit.moviehunt.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tdd.uchit.moviehunt.data.model.Data
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mGenre: TextView? = itemView.movie_item_genre
    private val mImage: ImageView? = itemView.movie_item_iv

    fun bindItem(data: Data) {
        mGenre?.text = data.genre
        Picasso.get().load(data.poster).into(mImage)
    }

}