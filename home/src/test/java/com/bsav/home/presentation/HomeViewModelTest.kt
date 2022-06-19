package com.bsav.home.presentation

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.usecase.GetProgramsByType
import com.bsav.testutils.TestCoroutineRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val getProgramsByType = mockk<GetProgramsByType>()
    private lateinit var viewModel: HomeViewModel
    private val observer = mockk<Observer<HomeViewModel.State>>()
    private val slot = slot<HomeViewModel.State>()
    private val states = arrayListOf<HomeViewModel.State>()
    private val programs = mockk<List<Program>>()

    @Before
    fun setup() {
        viewModel = HomeViewModel(getProgramsByType, testCoroutineRule.coroutineContextProvider)
        viewModel.state.observeForever(observer)
    }

    @After
    fun tearDown() {
        slot.clear()
        states.clear()
        viewModel.state.removeObserver(observer)
    }

    init {
        every { getProgramsByType(ProgramType.Movie.Popular) } answers { flowOf(programs) }
        every { getProgramsByType(ProgramType.Movie.TopRated) } answers { flowOf(programs) }
        every { getProgramsByType(ProgramType.TvShow.Popular) } answers { flowOf(programs) }
        every { getProgramsByType(ProgramType.TvShow.TopRated) } answers { flowOf(programs) }
        every { observer.onChanged(capture(slot)) } answers { states.add(slot.captured) }
    }

    @Test
    fun `when getPrograms is called then should call usecase and emit all states`() = runTest {
        viewModel.getPrograms()

        verify(exactly = 1) {
            getProgramsByType(ProgramType.Movie.Popular)
            getProgramsByType(ProgramType.Movie.TopRated)
            getProgramsByType(ProgramType.TvShow.Popular)
            getProgramsByType(ProgramType.TvShow.TopRated)
        }
        assert(states.size == 4)
        assert(states.contains(HomeViewModel.State.LoadPopularMovies(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedMovies(programs)))
        assert(states.contains(HomeViewModel.State.LoadPopularTvShows(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedTvShows(programs)))
    }

    @Test
    fun `given popular movies failed when getPrograms is called then should emit error and the other states`() = runTest {
        every { getProgramsByType(ProgramType.Movie.Popular) } answers { flow { throw NetworkErrorException() } }

        viewModel.getPrograms()

        verify(exactly = 1) {
            getProgramsByType(ProgramType.Movie.Popular)
            getProgramsByType(ProgramType.Movie.TopRated)
            getProgramsByType(ProgramType.TvShow.Popular)
            getProgramsByType(ProgramType.TvShow.TopRated)
        }
        assert(states.size == 4)
        assert(states.contains(HomeViewModel.State.Error))
        assert(states.contains(HomeViewModel.State.LoadTopRatedMovies(programs)))
        assert(states.contains(HomeViewModel.State.LoadPopularTvShows(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedTvShows(programs)))
    }

    @Test
    fun `given top rated movies failed when getPrograms is called then should emit error and the other states`() = runTest {
        every { getProgramsByType(ProgramType.Movie.TopRated) } answers { flow { throw NetworkErrorException() } }

        viewModel.getPrograms()

        verify(exactly = 1) {
            getProgramsByType(ProgramType.Movie.Popular)
            getProgramsByType(ProgramType.Movie.TopRated)
            getProgramsByType(ProgramType.TvShow.Popular)
            getProgramsByType(ProgramType.TvShow.TopRated)
        }
        assert(states.size == 4)
        assert(states.contains(HomeViewModel.State.LoadPopularMovies(programs)))
        assert(states.contains(HomeViewModel.State.Error))
        assert(states.contains(HomeViewModel.State.LoadPopularTvShows(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedTvShows(programs)))
    }

    @Test
    fun `given popular tv shows failed when getPrograms is called then should emit error and the other states`() = runTest {
        every { getProgramsByType(ProgramType.TvShow.Popular) } answers { flow { throw NetworkErrorException() } }

        viewModel.getPrograms()

        verify(exactly = 1) {
            getProgramsByType(ProgramType.Movie.Popular)
            getProgramsByType(ProgramType.Movie.TopRated)
            getProgramsByType(ProgramType.TvShow.Popular)
            getProgramsByType(ProgramType.TvShow.TopRated)
        }
        assert(states.size == 4)
        assert(states.contains(HomeViewModel.State.LoadPopularMovies(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedMovies(programs)))
        assert(states.contains(HomeViewModel.State.Error))
        assert(states.contains(HomeViewModel.State.LoadTopRatedTvShows(programs)))
    }

    @Test
    fun `given top rated tv shows failed when getPrograms is called then should emit error and the other states`() = runTest {
        every { getProgramsByType(ProgramType.TvShow.TopRated) } answers { flow { throw NetworkErrorException() } }

        viewModel.getPrograms()

        verify(exactly = 1) {
            getProgramsByType(ProgramType.Movie.Popular)
            getProgramsByType(ProgramType.Movie.TopRated)
            getProgramsByType(ProgramType.TvShow.Popular)
            getProgramsByType(ProgramType.TvShow.TopRated)
        }
        assert(states.size == 4)
        assert(states.contains(HomeViewModel.State.LoadPopularMovies(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedMovies(programs)))
        assert(states.contains(HomeViewModel.State.LoadPopularTvShows(programs)))
        assert(states.contains(HomeViewModel.State.Error))
    }

}