package com.nisa.mymoviecatalogueextension.domain.usecase

import com.nisa.mymoviecatalogueextension.data.enum.MovieType
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieListViewItem
import com.nisa.mymoviecatalogueextension.data.repository.RemoteRepository
import com.nisa.mymoviecatalogueextension.domain.mapper.MovieItemMapper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieListUseCase @Inject constructor(
    private val repository: RemoteRepository,
    private val itemMapper: MovieItemMapper
) {
    fun getMovies(movieType: MovieType, page: Int = 1, movieId: Int = 0): Observable<MovieListViewItem> {
        return when (movieType) {
            MovieType.NOW_PLAYING -> repository.getNowPlayingMovies(page).map { itemMapper.mapFrom(it) }
            MovieType.POPULAR -> repository.getPopularMovies(page).map { itemMapper.mapFrom(it) }
            MovieType.RECOMMENDATION -> repository.getRecommendationMovies(movieId).map { itemMapper.mapFrom(it) }
            MovieType.SIMILAR -> repository.getSimilarMovies(movieId).map { itemMapper.mapFrom(it) }
            MovieType.UPCOMING -> repository.getUpComingMovies(page).map { itemMapper.mapFrom(it) }
        }
    }
}