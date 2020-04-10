package com.mukesh.recyclerviewwithdatabinding.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mukesh.recyclerviewwithdatabinding.R
import com.mukesh.recyclerviewwithdatabinding.data.response.MovieItem
import com.mukesh.recyclerviewwithdatabinding.databinding.RecyclerviewMovieBinding

class MoviesAdapter(private val movies: List<MovieItem>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesHolder(
            DataBindingUtil.inflate<RecyclerviewMovieBinding>
                (
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_movie,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {

        holder.recyclerviewMovieBinding.movie = movies[position]
    }

    inner class MoviesHolder(val recyclerviewMovieBinding: RecyclerviewMovieBinding) :
        RecyclerView.ViewHolder(recyclerviewMovieBinding.root) {

    }
}