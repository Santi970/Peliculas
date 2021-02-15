package com.example.peliculas.repository

import com.example.peliculas.data.local.LocalMovieDataSource
import com.example.peliculas.data.model.MovieList
import com.example.peliculas.data.model.toMovieEntity
import com.example.peliculas.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
): MovieRespository{

    override suspend fun getUpcomingMovies(): MovieList {
        dataSourceRemote.getUpcomingMovies().results.forEach {movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
        }
        return dataSourceLocal.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        dataSourceRemote.getTopRatedMovies().results.forEach {movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("top_rated"))
        }
        return dataSourceLocal.getTopRatedMovies()
    }


    override suspend fun getPopularMovies(): MovieList {
        dataSourceRemote.getPopularMovies().results.forEach {movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
        }
        return dataSourceLocal.getPopularMovies()
    }
}

