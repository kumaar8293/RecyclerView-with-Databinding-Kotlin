package com.mukesh.recyclerviewwithdatabinding.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mukesh.recyclerviewwithdatabinding.utils.Coroutines
import com.mukesh.recyclerviewwithdatabinding.data.response.MovieItem
import com.mukesh.recyclerviewwithdatabinding.data.repository.MoviesRepository
import kotlinx.coroutines.Job

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {


    private val _movies by lazy {
        MutableLiveData<List<MovieItem>>()
    }

    // fun getMovies(): LiveData<List<MovieItem>> = movies_

    val movies: LiveData<List<MovieItem>> get() = _movies

    private lateinit var job: Job
    fun getMoviesListFromRepository() {

        job = Coroutines.ioToMainThread(
            { repository.getMovies() },
            {
                _movies.value = it
            }
        )


    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}
