package com.mukesh.recyclerviewwithdatabinding.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mukesh.recyclerviewwithdatabinding.R
import com.mukesh.recyclerviewwithdatabinding.data.response.MovieItem
import com.mukesh.recyclerviewwithdatabinding.databinding.MovieItemBinding

class MoviesAdapter(
    private val movies: List<MovieItem>,
    private val listener: RecyclerViewClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesHolder(
            DataBindingUtil.inflate<MovieItemBinding>
                (
                LayoutInflater.from(parent.context),
                R.layout.movie_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {

        holder.recyclerviewMovieBinding.movie = movies[position]
        holder.recyclerviewMovieBinding.buttonBook.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.recyclerviewMovieBinding.buttonBook,
                movies[position]
            )

        }
        holder.recyclerviewMovieBinding.imageViewLike.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.recyclerviewMovieBinding.imageViewLike,
                movies[position]
            )

        }
    }

    inner class MoviesHolder(val recyclerviewMovieBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(recyclerviewMovieBinding.root) {

    }
}