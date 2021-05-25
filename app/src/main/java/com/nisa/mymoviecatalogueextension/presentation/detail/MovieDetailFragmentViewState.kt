package com.nisa.mymoviecatalogueextension.presentation.detail

import android.view.View
import com.nisa.mymoviecatalogueextension.data.model.uimodel.CastViewItem
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieDetailViewItem
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieListViewItem

class MovieDetailFragmentViewState(
    private val movieDetailViewItem: MovieDetailViewItem,
    private val similarMovies: MovieListViewItem,
    private val casts: List<CastViewItem>,
    private val recommendationMovies: MovieListViewItem
) {

    fun getMovieDetailItems(): MovieDetailViewItem = movieDetailViewItem
    fun getSimilarMovies(): MovieListViewItem = similarMovies
    fun getCasts(): List<CastViewItem> = casts
    fun getRecommendationMovies(): MovieListViewItem = recommendationMovies

    fun getSimilarMoviesViewPagerTitle() = "Similar Movies"

    fun getRecommendationMoviesViewPagerTitle() = "Recommendation Movies"

    fun similarMoviesViewPagerVisibility(): Int = if (similarMovies.movies.isNotEmpty()) View.VISIBLE else View.GONE

    fun recommendationMoviesViewPagerVisibility(): Int = if (recommendationMovies.movies.isNotEmpty()) View.VISIBLE else View.GONE

    fun castAreaVisibility(): Int = if (casts.isNotEmpty()) View.VISIBLE else View.GONE

}
