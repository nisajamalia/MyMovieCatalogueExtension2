package com.nisa.mymoviecatalogueextension.domain.usecase

import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieDetailViewItem
import com.nisa.mymoviecatalogueextension.data.repository.RemoteRepository
import com.nisa.mymoviecatalogueextension.domain.mapper.MovieDetailItemMapper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailUseCase @Inject constructor(
    private val repository: RemoteRepository,
    private val itemMapper: MovieDetailItemMapper
) {
    fun getMovieDetail(movieId: Int): Observable<MovieDetailViewItem> {
        return repository.getMovieDetail(movieId = movieId).map { itemMapper.mapFrom(it) }
    }
}



