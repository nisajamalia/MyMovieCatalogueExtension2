package com.nisa.mymoviecatalogueextension.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.google.common.truth.Truth
import com.nisa.mymoviecatalogueextension.RxImmediateSchedulerRule
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieListViewItem
import com.nisa.mymoviecatalogueextension.data.repository.RemoteRepository
import com.nisa.mymoviecatalogueextension.domain.decider.MovieItemDecider
import com.nisa.mymoviecatalogueextension.domain.mapper.MovieItemMapper
import com.nisa.mymoviecatalogueextension.domain.usecase.SearchMovieUseCase
import com.nisa.mymoviecatalogueextension.util.Constants
import com.nisa.mymoviecatalogueextension.util.TestUtil
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SearchMovieUseCaseTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    lateinit var remoteRepository: RemoteRepository

    @InjectMockKs
    lateinit var movieItemMapper: MovieItemMapper

    @InjectMockKs
    lateinit var movieItemDecider: MovieItemDecider

    private lateinit var useCase: SearchMovieUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = SearchMovieUseCase(remoteRepository, movieItemMapper)
    }

    @Test
    fun `when searchMovie called then return observable data`() {
        // GIVEN
        val page = 10
        val query = "query"
        val mockedMovieResponse = TestUtil.getMockedMovieResponse("searched", 10)

        every { remoteRepository.searchMovie(query, page) } returns Observable.just(mockedMovieResponse)

        val testObserver = TestObserver<MovieListViewItem>()

        // WHEN
        val result = useCase.searchMovie(query, page)
        result.subscribe(testObserver)
        val listResult = testObserver.values().first()

        // THEN
        verify { remoteRepository.searchMovie(query, page) }
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        Truth.assertThat(listResult.movies.first().title).isEqualTo("searched")
        Truth.assertThat(listResult.movies.first().imagePath).isEqualTo("${Constants.PROFILE_PATH}${mockedMovieResponse.results?.first()?.backdropPath}")
        Truth.assertThat(listResult.page).isEqualTo(mockedMovieResponse.page)
        Truth.assertThat(listResult.totalPage).isEqualTo(mockedMovieResponse.totalPages)

    }
}