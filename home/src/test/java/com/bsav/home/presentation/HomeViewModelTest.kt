package com.bsav.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.usecase.GetProgramsByType
import com.bsav.testutils.TestCoroutineRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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

    @Before
    fun setup() {
        viewModel = HomeViewModel(getProgramsByType, testCoroutineRule.coroutineContextProvider)
    }

    @Test
    fun `when getPrograms is called then should call usecase and emit state`() {
        val programs = mockk<List<Program>>()
        every { getProgramsByType(ProgramType.Movie.Popular) } answers { flowOf(programs) }
        every { getProgramsByType(ProgramType.Movie.TopRated) } answers { flowOf(programs) }
        every { getProgramsByType(ProgramType.TvShow.Popular) } answers { flowOf(programs) }
        every { getProgramsByType(ProgramType.TvShow.TopRated) } answers { flowOf(programs) }

        val slot = slot<HomeViewModel.State>()
        val states = arrayListOf<HomeViewModel.State>()

        viewModel.state.observeForever(observer)
        every { observer.onChanged(capture(slot)) } answers { states.add(slot.captured) }

        viewModel.getPrograms()

        verifyOrder {
            getProgramsByType(ProgramType.Movie.Popular)
            getProgramsByType(ProgramType.Movie.TopRated)
            getProgramsByType(ProgramType.TvShow.Popular)
            getProgramsByType(ProgramType.TvShow.TopRated)
        }
        assert(states.contains(HomeViewModel.State.LoadPopularMovies(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedMovies(programs)))
        assert(states.contains(HomeViewModel.State.LoadPopularTvShows(programs)))
        assert(states.contains(HomeViewModel.State.LoadTopRatedTvShows(programs)))
    }

}