package com.mukesh.recyclerviewwithdatabinding.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mukesh.recyclerviewwithdatabinding.data.repository.MoviesRepository

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(private val moviesRepository: MoviesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return MoviesViewModel(
            moviesRepository
        ) as T
    }
}