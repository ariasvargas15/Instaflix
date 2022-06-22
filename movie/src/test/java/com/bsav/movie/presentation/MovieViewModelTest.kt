package com.bsav.movie.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bsav.movie.domain.model.Movie
import com.bsav.movie.domain.usecase.GetMovieById
import com.bsav.networkhelper.NetworkHelper
import com.bsav.testutils.TestCoroutineRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val getMovieById = mockk<GetMovieById>(relaxed = true)
    private val networkHelper = mockk<NetworkHelper>(relaxed = true)
    private lateinit var viewModel: MovieViewModel
    private val stateObserver = mockk<Observer<MovieViewModel.State>>()
    private val statesSlot = slot<MovieViewModel.State>()
    private val states = mutableListOf<MovieViewModel.State>()

    @Before
    fun setup() {
        viewModel = MovieViewModel(
            getMovieById,
            networkHelper,
            testCoroutineRule.coroutineContextProvider
        )
        viewModel.state.observeForever(stateObserver)
    }

    @After
    fun tearDown() {
        statesSlot.clear()
        states.clear()
        viewModel.state.removeObserver(stateObserver)
    }

    init {
        every { stateObserver.onChanged(capture(statesSlot)) } answers { states.add(statesSlot.captured) }
    }

    @Test
    fun `given internet when getMovie is called then should check internet connection and not emit state`() {
        every { networkHelper.isInternetAvailable() } answers { true }

        viewModel.getMovie(1)

        verify(exactly = 1) {
            networkHelper.isInternetAvailable()
        }
        assert(states.contains(MovieViewModel.State.NoInternetAvailable).not())
    }

    @Test
    fun `given no internet when getMovie is called then should check internet connection and emit state`() {
        every { networkHelper.isInternetAvailable() } answers { false }

        viewModel.getMovie(1)

        verify(exactly = 1) {
            networkHelper.isInternetAvailable()
        }
        assert(states.contains(MovieViewModel.State.NoInternetAvailable))
    }

    @Test
    fun `when getPrograms is called then should call usecase and emit all states`() {
        val movie = mockk<Movie>()
        every { getMovieById(1) } answers { flowOf(movie) }

        viewModel.getMovie(1)

        verify(exactly = 1) {
            getMovieById(1)
        }
        assert(states.contains(MovieViewModel.State.LoadMovie(movie)))
    }

    @Test
    fun `given exception when getMovie is called then should call usecase and emit unexpected error`() {
        every { networkHelper.isInternetAvailable() } answers { true }
        every { getMovieById(1) } answers { flow { throw Exception() } }

        viewModel.getMovie(1)

        verify(exactly = 1) {
            getMovieById(1)
        }
        assert(states.contains(MovieViewModel.State.UnexpectedError))
    }
}
