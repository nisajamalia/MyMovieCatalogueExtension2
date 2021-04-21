package com.nisa.mymoviecatalogueextension.domain.repository

import com.nisa.mymoviecatalogueextension.data.model.response.CastResponse
import com.nisa.mymoviecatalogueextension.data.model.response.MovieDetailReponse
import com.nisa.mymoviecatalogueextension.data.model.response.MovieResponse
import io.reactivex.Observable

interface IMovieRepository {
    fun getPopularMovies(page: Int): Observable<MovieResponse>
    fun getUpComingMovies(page: Int): Observable<MovieResponse>
    fun getNowPlayingMovies(page: Int): Observable<MovieResponse>
    fun getSimilarMovies(movieId: Int): Observable<MovieResponse>
    fun getRecommendationMovies(movieId: Int): Observable<MovieResponse>
    fun getMovieCredits(movieId: Int): Observable<CastResponse>
    fun getMovieDetail(movieId: Int): Observable<MovieDetailReponse>
    fun searchMovie(query: String, page: Int): Observable<MovieResponse>
}