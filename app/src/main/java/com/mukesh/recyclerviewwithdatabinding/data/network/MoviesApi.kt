package com.mukesh.recyclerviewwithdatabinding.data.network

import com.mukesh.recyclerviewwithdatabinding.data.response.MovieItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MoviesApi {

    companion object {
        //Whenever we will write MoviesApi(),it will call invoke() function automatically

        operator fun invoke(): MoviesApi {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.androidhive.info/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }

    }

    @GET("movies.json")
    suspend fun getMoviesList(): Response<List<MovieItem>>
}