package com.nisa.mymoviecatalogueextension.domain.mapper

import com.nisa.mymoviecatalogueextension.base.extension.orZero
import com.nisa.mymoviecatalogueextension.data.model.response.MovieResponse
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieListViewItem
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieViewItem
import com.nisa.mymoviecatalogueextension.domain.decider.MovieItemDecider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieItemMapper @Inject constructor(private val itemDecider: MovieItemDecider) :
    Mapper<MovieResponse, MovieListViewItem?> {
    override fun mapFrom(item: MovieResponse): MovieListViewItem? {
        return MovieListViewItem(
            page = item.page.orZero(),
            totalPage = item.totalPages.orZero(),
            movies = item.results?.map { movie ->
                MovieViewItem(
                    id = movie.id.orZero(),
                    imagePath = itemDecider.provideImagePath(movie.posterPath).orEmpty(),
                    title = movie.title.orEmpty()
                )
            }.orEmpty()
        )
    }
}