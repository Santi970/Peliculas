package com.example.peliculas.repository

import com.example.peliculas.data.model.MovieList

interface MovieRespository {

    suspend fun getUpcomingMovies(): MovieList

    suspend fun getTopRatedMovies(): MovieList

    suspend fun getPopularMovies(): MovieList
}