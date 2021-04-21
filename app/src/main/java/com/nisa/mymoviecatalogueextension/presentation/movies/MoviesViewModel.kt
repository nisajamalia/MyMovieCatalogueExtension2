package com.nisa.mymoviecatalogueextension.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nisa.mymoviecatalogueextension.base.viewmodel.BaseViewModel
import com.nisa.mymoviecatalogueextension.data.enum.MovieType
import com.nisa.mymoviecatalogueextension.domain.usecase.MovieListUseCase
import io.reactivex.rxkotlin.Observables
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase
)
    : BaseViewModel()
{
    /** LiveData for ViewState **/
    private val liveDataViewState = MutableLiveData<MoviesFragmentViewState>()
    val liveDataViewState_: LiveData<MoviesFragmentViewState> = liveDataViewState

    fun getMovies() {
        return Observables.zip(
            movieListUseCase.getMovies(MovieType.POPULAR),
            movieListUseCase.getMovies(MovieType.NOW_PLAYING),
            movieListUseCase.getMovies(MovieType.UPCOMING),
            MoviesItemCombiner()
        ).sendRequest {
            liveDataViewState.value = it
        }

    }

}