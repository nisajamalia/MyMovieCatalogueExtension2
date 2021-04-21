package com.nisa.mymoviecatalogueextension.presentation.list

import com.nisa.mymoviecatalogueextension.data.enum.MovieListPageType
import com.nisa.mymoviecatalogueextension.data.enum.MovieType


class MovieListFragmentViewState(private val pageType: MovieListPageType, private val searchQuery: String?) {
    fun getPageType(): MovieListPageType = pageType
    fun getSearchQuery(): String? = searchQuery

    fun getPageTitle(): String =
        when (pageType) {
            MovieListPageType.UPCOMING -> "Upcoming Movies"
            MovieListPageType.POPULAR -> "Popular Movies"
            MovieListPageType.SEARCH -> "Search Results For '${searchQuery?.toUpperCase()}'"
        }
}