package com.example.peliculas.repository

import com.example.peliculas.data.model.MovieList
import com.example.peliculas.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl (private val dataSourceRemote: RemoteMovieDataSource): MovieRespository{

    override suspend fun getUpcomingMovies(): MovieList = dataSourceRemote.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSourceRemote.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSourceRemote.getPopularMovies()
}

