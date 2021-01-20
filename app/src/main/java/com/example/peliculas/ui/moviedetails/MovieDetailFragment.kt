package com.example.peliculas.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.peliculas.R
import com.example.peliculas.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.posterimageurl}")
            .centerCrop()
            .into(binding.imgMovie)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.backgroundimageUrl}")
            .centerCrop()
            .into(binding.imgBackground)

        binding.txtDescription.text = args.overview
        binding.textViewMovieTitle.text = args.title
        binding.txtLanguage.text = args.lenguage
        binding.txtRating.text = "Language ${args.lenguage}"
        binding.txtRating.text = "${args.voteAverage} (${args.voteCount}) Reviews "
        binding.txtReleased.text = "Released ${args.releaseDate}"


    }
}