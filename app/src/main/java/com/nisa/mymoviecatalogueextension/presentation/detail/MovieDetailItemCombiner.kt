package com.nisa.mymoviecatalogueextension.presentation.detail

import com.nisa.mymoviecatalogueextension.data.model.uimodel.CastViewItem
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieDetailViewItem
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieListViewItem


class MovieDetailItemCombiner : (MovieDetailViewItem, MovieListViewItem, List<CastViewItem>, MovieListViewItem) -> MovieDetailFragmentViewState {
    override fun invoke(p1: MovieDetailViewItem, p2: MovieListViewItem, p3: List<CastViewItem>, p4: MovieListViewItem): MovieDetailFragmentViewState {
        return MovieDetailFragmentViewState(movieDetailViewItem = p1, similarMovies = p2, casts = p3, recommendationMovies = p4)
    }
}