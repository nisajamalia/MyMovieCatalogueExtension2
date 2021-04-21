package com.nisa.mymoviecatalogueextension.domain.usecase

import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieListViewItem
import com.nisa.mymoviecatalogueextension.data.repository.RemoteRepository
import com.nisa.mymoviecatalogueextension.domain.mapper.MovieItemMapper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMovieUseCase @Inject constructor(
    private val repository: RemoteRepository,
    private val itemMapper: MovieItemMapper
) {
    fun searchMovie(query: String, page: Int): Observable<MovieListViewItem> {
        return repository.searchMovie(query, page).map {
            itemMapper.mapFrom(it)
        }
    }
}

