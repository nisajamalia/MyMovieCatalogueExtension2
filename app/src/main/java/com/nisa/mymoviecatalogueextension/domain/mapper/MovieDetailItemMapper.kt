package com.nisa.mymoviecatalogueextension.domain.mapper

import com.nisa.mymoviecatalogueextension.base.extension.orZero
import com.nisa.mymoviecatalogueextension.base.extension.simpleDateConvert
import com.nisa.mymoviecatalogueextension.data.model.response.MovieDetailReponse
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieDetailViewItem
import com.nisa.mymoviecatalogueextension.domain.decider.MovieDetailItemDecider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailItemMapper @Inject constructor(private val itemDecider: MovieDetailItemDecider) :
    Mapper<MovieDetailReponse, MovieDetailViewItem> {
    override fun mapFrom(item: MovieDetailReponse): MovieDetailViewItem {
        return MovieDetailViewItem(
            id = item.id.orZero(),
            backdropPath = itemDecider.provideBackdropPath(item.backdropPath).orEmpty(),
            genres = item.genres.orEmpty(),
            title = item.title.orEmpty(),
            overview = item.overview.orEmpty(),
            popularity = item.popularity.orZero(),
            imagePath = itemDecider.provideImagePath(item.posterPath).orEmpty(),
            releaseDate = item.releaseDate.simpleDateConvert("dd MMM yyyy").orEmpty(),
            runtime = "${item.runtime} min",
            status = item.status.orEmpty(),
            voteAverage = item.voteAverage?.toFloat().orZero() / 2
        )
    }
}