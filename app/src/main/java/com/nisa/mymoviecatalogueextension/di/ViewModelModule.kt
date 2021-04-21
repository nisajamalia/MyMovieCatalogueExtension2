package com.nisa.mymoviecatalogueextension.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nisa.mymoviecatalogueextension.presentation.detail.MovieDetailViewModel
import com.nisa.mymoviecatalogueextension.presentation.list.MovieListViewModel
import com.nisa.mymoviecatalogueextension.presentation.main.MainViewModel
import com.nisa.mymoviecatalogueextension.presentation.movies.MoviesViewModel
import com.nisa.mymoviecatalogueextension.presentation.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindsMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsHomeViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindsSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindsMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindsMovieListViewModel(viewModel: MovieListViewModel): ViewModel

}