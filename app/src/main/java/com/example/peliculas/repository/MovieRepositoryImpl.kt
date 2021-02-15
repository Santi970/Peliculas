package com.example.peliculas.repository

import com.example.peliculas.core.InternetCheck
import com.example.peliculas.data.local.LocalMovieDataSource
import com.example.peliculas.data.model.MovieList
import com.example.peliculas.data.model.toMovieEntity
import com.example.peliculas.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
): MovieRespository{

    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }

    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach {movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("top_rated"))
            }
             dataSourceLocal.getTopRatedMovies()
        }else{
             dataSourceLocal.getTopRatedMovies()
        }

    }


    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach {movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        }else{
            dataSourceLocal.getPopularMovies()
        }

    }
}

