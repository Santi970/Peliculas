package com.example.peliculas.data.remote

import com.example.peliculas.Application.Constantes
import com.example.peliculas.data.model.MovieList
import com.example.peliculas.repository.WebService


class RemoteMovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(Constantes.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(Constantes.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(Constantes.API_KEY)

}