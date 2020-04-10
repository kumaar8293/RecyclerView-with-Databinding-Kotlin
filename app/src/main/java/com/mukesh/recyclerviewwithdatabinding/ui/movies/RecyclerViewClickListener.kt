package com.mukesh.recyclerviewwithdatabinding.ui.movies

import android.view.View
import com.mukesh.recyclerviewwithdatabinding.data.response.MovieItem

interface RecyclerViewClickListener {

    fun onRecyclerViewItemClick(view: View, movieItem: MovieItem)
}