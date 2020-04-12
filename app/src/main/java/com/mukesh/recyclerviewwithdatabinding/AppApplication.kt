package com.mukesh.recyclerviewwithdatabinding

import android.app.Application
import com.mukesh.recyclerviewwithdatabinding.data.network.MoviesApi
import com.mukesh.recyclerviewwithdatabinding.data.repository.MoviesRepository
import com.mukesh.recyclerviewwithdatabinding.ui.movies.MoviesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        /**
         * Steps to Use Koein
         * 1-> First implement the KodeinAware interface
         * 2-> Initialize Kodein with Lazy initialization
         * 3-> Initialize all the Classes using bind()
         */
        import(androidXModule(this@AppApplication))

        // MoviesApi() takes NetworkConnectionInterceptor as a parameter


        //We can pass instance() method and Kodein will take care of it
        bind() from singleton { MoviesApi() }
        bind() from singleton { MoviesRepository(instance()) }

        //MoviesViewModelFactory doesn't should be singleton so we will user provider
        //MoviesViewModelFactory needs UserRepository
        bind() from provider { MoviesViewModelFactory(instance()) }


    }

}