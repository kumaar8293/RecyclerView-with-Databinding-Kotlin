package com.mukesh.recyclerviewwithdatabinding.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mukesh.recyclerviewwithdatabinding.R
import com.mukesh.recyclerviewwithdatabinding.data.response.MovieItem
import com.mukesh.recyclerviewwithdatabinding.utils.ApiException
import com.mukesh.recyclerviewwithdatabinding.utils.NoInternetException
import kotlinx.android.synthetic.main.movies_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.lang.Exception


class MoviesFragment : Fragment(), RecyclerViewClickListener, KodeinAware {
    //This is how we get instances fom Kodein
    // You will get an error with kodein() method then
    // You need to import import org.kodein.di.android.x.kodein manually
    override val kodein: Kodein by kodein()
    private val factory: MoviesViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* I replaced it with Kodein Dependency Injection

         val api = MoviesApi()
          val repository =
              MoviesRepository(
                  api
              )
          val factory: MoviesViewModelFactory =
              MoviesViewModelFactory(
                  repository
              )*/
        val viewModel = ViewModelProvider(this, factory).get(MoviesViewModel::class.java)


        try {
            viewModel.getMoviesListFromRepository()

        } catch (e: ApiException) {

        } catch (noInternet: NoInternetException) {

        }

        progress_bar.visibility = View.VISIBLE
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recycler_view.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter =
                    MoviesAdapter(
                        movies,
                        this
                    )
                progress_bar.visibility = View.INVISIBLE

            }
        })

    }

    override fun onRecyclerViewItemClick(view: View, movieItem: MovieItem) {

        when (view.id) {

            R.id.button_book ->
                Toast.makeText(requireContext(), "Button Booked", Toast.LENGTH_SHORT).show()

            R.id.imageViewLike ->
                Toast.makeText(requireContext(), "Like Clicked", Toast.LENGTH_SHORT).show()

        }

    }

}
