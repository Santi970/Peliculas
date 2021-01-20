package com.example.peliculas.repository

import com.example.peliculas.data.model.MovieList
import com.example.peliculas.data.remote.MovieDataSource

class MovieRepositoryImpl (private val dataSource: MovieDataSource): MovieRespository{

    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()
}

