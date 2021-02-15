package com.example.peliculas.data.local

import com.example.peliculas.Application.Constantes
import com.example.peliculas.data.model.MovieEntity
import com.example.peliculas.data.model.MovieList
import com.example.peliculas.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter{it.movie_type == "upcoming"}.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter{it.movie_type == "top_rated"}.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter{it.movie_type == "popular"}.toMovieList()
    }

    suspend fun saveMovie(movie:MovieEntity){
        movieDao.saveMovie(movie)
    }
}