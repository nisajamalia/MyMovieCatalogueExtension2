package com.nisa.mymoviecatalogueextension.presentation.movies

import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieListViewItem


class MoviesItemCombiner : (MovieListViewItem, MovieListViewItem, MovieListViewItem) -> MoviesFragmentViewState {
    override fun invoke(p1: MovieListViewItem, p2: MovieListViewItem, p3: MovieListViewItem): MoviesFragmentViewState {
        return MoviesFragmentViewState(popularMovies = p1, nowPlayingMovies = p2, upComingMovies = p3)
    }
}