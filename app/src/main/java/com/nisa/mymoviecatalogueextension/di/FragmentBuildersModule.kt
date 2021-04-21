package com.nisa.mymoviecatalogueextension.di

import com.nisa.mymoviecatalogueextension.presentation.detail.MovieDetailFragment
import com.nisa.mymoviecatalogueextension.presentation.list.MovieListFragment
import com.nisa.mymoviecatalogueextension.presentation.movies.MoviesFragment
import com.nisa.mymoviecatalogueextension.presentation.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment
}
