package com.mukesh.recyclerviewwithdatabinding.data.repository

import com.mukesh.recyclerviewwithdatabinding.data.network.MoviesApi
import com.mukesh.recyclerviewwithdatabinding.data.network.SafeApiRequest

class MoviesRepository(private val api: MoviesApi) : SafeApiRequest() {

    suspend fun getMovies() = makeApiRequest {
        api.getMoviesList()
    }

}