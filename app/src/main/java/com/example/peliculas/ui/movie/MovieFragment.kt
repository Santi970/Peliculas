package com.example.peliculas.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.peliculas.R
import com.example.peliculas.core.Resource
import com.example.peliculas.data.local.AppDatabase
import com.example.peliculas.data.local.LocalMovieDataSource
import com.example.peliculas.data.model.Movie
import com.example.peliculas.data.remote.RemoteMovieDataSource
import com.example.peliculas.databinding.FragmentMovieBinding
import com.example.peliculas.presentation.MovieViewModel
import com.example.peliculas.presentation.MovieViewModelFactory
import com.example.peliculas.repository.MovieRepositoryImpl
import com.example.peliculas.repository.RetrofitClient
import com.example.peliculas.ui.movie.adapters.MovieAdapter
import com.example.peliculas.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.peliculas.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.peliculas.ui.movie.adapters.concat.UpComingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.onMovieOnClickListener {

    private lateinit var binding : FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webService),
                LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Resource.Loading ->{
                    binding.ProgressBar.visibility = View.VISIBLE
                }
                is Resource.Success ->{
                    binding.ProgressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpComingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                        addAdapter(1, TopRatedConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
                    }

                    binding.rvMovies.adapter = concatAdapter

                }
                is Resource.Failure ->{
                    binding.ProgressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }
            }

        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_avarage.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )

        findNavController().navigate(action)

    }
}