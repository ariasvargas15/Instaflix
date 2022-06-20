package com.bsav.home.presentation

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bsav.core.utils.Event
import com.bsav.core.utils.NetworkHelper
import com.bsav.home.domain.model.Destination
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.usecase.GetProgramsByType
import com.bsav.home.domain.usecase.navigator.ProgramNavigator
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
class HomeViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val getProgramsByType = mockk<GetProgramsByType>()
    private val programNavigator = mockk<ProgramNavigator>()
    private val networkHelper = mockk<NetworkHelper>()
    private lateinit var viewModel: HomeViewModel
    private val stateObserver = mockk<Observer<HomeViewModel.State>>()
    private val statesSlot = slot<HomeViewModel.State>()
    private val states = mutableListOf<HomeViewModel.State>()
    private val destinationObserver = mockk<Observer<Event<Destination>>>()
    private val destinationSlot = slot<Event<Destination>>()
    private val destinations = mutableListOf<Event<Destination>>()
    private val programs = mockk<List<Program>>()

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            getProgramsByType,
            programNavigator,
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
        every { networkHelper.isInternetAvailable() } answers { true }
        every { getProgramsByType(ProgramType.Movie.Popular) } answers { flowOf(programs) }
        every { getProgramsByType(ProgramType.Movie.TopRated) } answers { flowOf(programs) }
        every { getProgramsByType(ProgramType.TvShow.Popular) } answers { flowOf(programs) }
        every { getProgramsByType(ProgramType.TvShow.TopRated) } answers { flowOf(programs) }
        every { stateObserver.onChanged(capture(statesSlot)) } answers { states.add(statesSlot.captured) }
        every { destinationObserver.onChanged(capture(destinationSlot)) } answers { destinations.add(destinationSlot.captured) }
    }

    @Test
    fun `given internet when getPrograms is called then should check internet connection and not emit state`() {
        viewModel.getPrograms()

        verify(exactly = 1) {
            networkHelper.isInternetAvailable()
        }
        assert(states.contains(HomeViewModel.State.NoInternetAvailable).not())
    }

    @Test
    fun `given no internet when getPrograms is called then should check internet connection and emit state`() {
        every { networkHelper.isInternetAvailable() } answers { false }

        viewModel.getPrograms()

        verify(exactly = 1) {
            networkHelper.isInternetAvailable()
        }
        assert(states.contains(HomeViewModel.State.NoInternetAvailable))
    }

    @Test
    fun `when getPrograms is called then should call usecase and emit all states`() {
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
    fun `given popular movies failed when getPrograms is called then should emit error and the other states`() {
        every { getProgramsByType(ProgramType.Movie.Popular) } answers { flow { throw NetworkErrorException() } }

        viewModel.getPrograms()

        verify(exactly = 1) {
            getProgramsByType(ProgramType.Movie.Popular)
            getProgramsByType(ProgramType.Movie.TopRated)
            getProgramsByType(ProgramType.TvShow.Popular)
            getProgramsByType(ProgramType.TvShow.TopRated)
        }
        assert(states.size == 4)
        assert(states.contains(HomeViewModel.State.UnexpectedError))
        assert(states.contains(HomeViewModel.State.LoadTopRatedMovies(programs)))
        assert(states.contains(HomeViewModel.State.LoadPopularTvShows(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedTvShows(programs)))
    }

    @Test
    fun `given top rated movies failed when getPrograms is called then should emit error and the other states`() {
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
        assert(states.contains(HomeViewModel.State.UnexpectedError))
        assert(states.contains(HomeViewModel.State.LoadPopularTvShows(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedTvShows(programs)))
    }

    @Test
    fun `given popular tv shows failed when getPrograms is called then should emit error and the other states`() {
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
        assert(states.contains(HomeViewModel.State.UnexpectedError))
        assert(states.contains(HomeViewModel.State.LoadTopRatedTvShows(programs)))
    }

    @Test
    fun `given top rated tv shows failed when getPrograms is called then should emit error and the other states`() {
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
        assert(states.contains(HomeViewModel.State.UnexpectedError))
    }

    @Test
    fun `when goToDetail is called then should emit NavigateTo state`() {
        val params = mapOf("programId" to 1)
        val destination = mockk<Destination>()
        every {
            programNavigator.resolveDestination(params, ProgramType.Movie.Popular)
        } answers {
            destination
        }

        viewModel.navigate.observeForever(destinationObserver)
        viewModel.goToDetail(1, ProgramType.Movie.Popular)
        viewModel.navigate.removeObserver(destinationObserver)

        verify(exactly = 1) {
            programNavigator.resolveDestination(params, ProgramType.Movie.Popular)
        }
        assert(destinations.contains(Event(destination)))
    }
}
