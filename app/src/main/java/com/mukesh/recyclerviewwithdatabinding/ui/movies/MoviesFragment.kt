package com.mukesh.recyclerviewwithdatabinding.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mukesh.recyclerviewwithdatabinding.*
import com.mukesh.recyclerviewwithdatabinding.data.repository.MoviesRepository
import com.mukesh.recyclerviewwithdatabinding.data.network.MoviesApi
import kotlinx.android.synthetic.main.movies_fragment.*


class MoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = MoviesApi()
        val repository =
            MoviesRepository(
                api
            )
        val factory: MoviesViewModelFactory =
            MoviesViewModelFactory(
                repository
            )
        val viewModel = ViewModelProvider(this, factory).get(MoviesViewModel::class.java)

        viewModel.getMoviesListFromRepository()
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recycler_view.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter =
                    MoviesAdapter(
                        movies
                    )
            }
        })

    }

}
