package com.nisa.mymoviecatalogueextension.usecase
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.nisa.mymoviecatalogueextension.RxImmediateSchedulerRule
import com.nisa.mymoviecatalogueextension.data.enum.MovieType
import com.nisa.mymoviecatalogueextension.data.model.uimodel.MovieListViewItem
import com.nisa.mymoviecatalogueextension.data.repository.RemoteRepository
import com.nisa.mymoviecatalogueextension.domain.decider.MovieItemDecider
import com.nisa.mymoviecatalogueextension.domain.mapper.MovieItemMapper
import com.nisa.mymoviecatalogueextension.domain.usecase.MovieListUseCase
import com.nisa.mymoviecatalogueextension.util.Constants
import com.nisa.mymoviecatalogueextension.util.TestUtil
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesListUseCaseTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    lateinit var remoteRepository: RemoteRepository

    @InjectMockKs
    lateinit var movieItemDecider: MovieItemDecider

    @InjectMockKs
    lateinit var movieItemMapper: MovieItemMapper

    private lateinit var useCase: MovieListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = MovieListUseCase(remoteRepository, movieItemMapper)


    }

    @Test
    fun `given movie type is NOW_PLAYING when getMovies called then return observable data`() {
        // GIVEN
        val mockedMovieResponse = TestUtil.getMockedMovieResponse("NOW PLAYING")
        every { remoteRepository.getNowPlayingMovies(1) } returns Observable.just(mockedMovieResponse)

        val testObserver = TestObserver<MovieListViewItem>()

        // WHEN
        val result = useCase.getMovies(MovieType.NOW_PLAYING)


        // THEN
        result.subscribe(testObserver)
        val listResult = testObserver.values().first()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        Truth.assertThat(listResult.movies.first().title).isEqualTo("NOW PLAYING")
        Truth.assertThat(listResult.movies.first().imagePath).isEqualTo("${Constants.PROFILE_PATH}${mockedMovieResponse.results?.first()?.backdropPath}")

    }
}