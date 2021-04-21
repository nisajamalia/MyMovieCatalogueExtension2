package com.nisa.mymoviecatalogueextension.domain.usecase

import com.nisa.mymoviecatalogueextension.data.model.uimodel.CastViewItem
import com.nisa.mymoviecatalogueextension.data.repository.RemoteRepository
import com.nisa.mymoviecatalogueextension.domain.mapper.CastItemMapper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieCreditUseCase @Inject constructor(
    private val repository: RemoteRepository,
    private val castItemMapper: CastItemMapper
) {
    fun getMovieCredits(movieId: Int): Observable<List<CastViewItem>> {
        return repository.getMovieCredits(movieId).map {
            castItemMapper.mapFrom(it).orEmpty()
        }
    }
}

