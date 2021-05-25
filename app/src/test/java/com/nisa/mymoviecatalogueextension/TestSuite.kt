package com.nisa.mymoviecatalogueextension


import com.nisa.mymoviecatalogueextension.domain.usecase.MovieCreditUseCase
import com.nisa.mymoviecatalogueextension.domain.usecase.MovieDetailUseCase
import com.nisa.mymoviecatalogueextension.domain.usecase.SearchMovieUseCase
import com.nisa.mymoviecatalogueextension.usecase.MoviesListUseCaseTest
import com.nisa.mymoviecatalogueextension.viewmodel.MovieDetailViewModelTest
import com.nisa.mymoviecatalogueextension.viewmodel.MoviesViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    MoviesViewModelTest::class,
    MovieDetailViewModelTest::class,
    MoviesListUseCaseTest::class,
    MovieCreditUseCase::class,
    MovieDetailUseCase::class,
    SearchMovieUseCase::class
)
class TestSuite
