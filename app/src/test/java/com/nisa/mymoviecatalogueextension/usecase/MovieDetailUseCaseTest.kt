package com.nisa.mymoviecatalogueextension.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.nisa.mymoviecatalogueextension.RxImmediateSchedulerRule
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieDetailViewItem
import com.nisa.mymoviecatalogueextension.data.repository.RemoteRepository
import com.nisa.mymoviecatalogueextension.domain.decider.MovieDetailItemDecider
import com.nisa.mymoviecatalogueextension.domain.mapper.MovieDetailItemMapper
import com.nisa.mymoviecatalogueextension.domain.usecase.MovieDetailUseCase
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

class MovieDetailUseCaseTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    lateinit var remoteRepository: RemoteRepository

    @InjectMockKs
    lateinit var movieDetailItemMapper: MovieDetailItemMapper

    @InjectMockKs
    lateinit var movieDetailItemDecider: MovieDetailItemDecider

    private lateinit var useCase: MovieDetailUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = MovieDetailUseCase(remoteRepository, movieDetailItemMapper)
    }

    @Test
    fun `when getMovieDetail called then return observable data`() {
        // GIVEN
        val mockedCastResponse = TestUtil.getMockedMovieDetailResponse("batman")
        every { remoteRepository.getMovieDetail(any()) } returns Observable.just(mockedCastResponse)

        val testObserver = TestObserver<MovieDetailViewItem>()

        // WHEN
        val result = useCase.getMovieDetail(5)
        result.subscribe(testObserver)
        val listResult = testObserver.values().first()

        // THEN
        verify { remoteRepository.getMovieDetail(any()) }
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        Truth.assertThat(listResult.title).isEqualTo("batman")
        Truth.assertThat(listResult.backdropPath).isEqualTo("${Constants.BACKDROP_PATH}${mockedCastResponse.backdropPath}")
        Truth.assertThat(listResult.imagePath).isEqualTo("${Constants.POSTER_PATH}${mockedCastResponse.posterPath}")


    }
}