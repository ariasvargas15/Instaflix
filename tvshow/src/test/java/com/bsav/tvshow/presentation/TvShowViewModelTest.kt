package com.bsav.tvshow.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bsav.networkhelper.NetworkHelper
import com.bsav.testutils.TestCoroutineRule
import com.bsav.tvshow.domain.model.TvShow
import com.bsav.tvshow.domain.usecase.GetTvShowById
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
class TvShowViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val getTvShowById = mockk<GetTvShowById>(relaxed = true)
    private val networkHelper = mockk<NetworkHelper>(relaxed = true)
    private lateinit var viewModel: TvShowViewModel
    private val stateObserver = mockk<Observer<TvShowViewModel.State>>()
    private val statesSlot = slot<TvShowViewModel.State>()
    private val states = mutableListOf<TvShowViewModel.State>()

    @Before
    fun setup() {
        viewModel = TvShowViewModel(
            getTvShowById,
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
    fun `given internet when getTvShow is called then should check internet connection and not emit state`() {
        every { networkHelper.isInternetAvailable() } answers { true }

        viewModel.getTvShow(1)

        verify(exactly = 1) {
            networkHelper.isInternetAvailable()
        }
        assert(states.contains(TvShowViewModel.State.NoInternetAvailable).not())
    }

    @Test
    fun `given no internet when getTvShow is called then should check internet connection and emit state`() {
        every { networkHelper.isInternetAvailable() } answers { false }

        viewModel.getTvShow(1)

        verify(exactly = 1) {
            networkHelper.isInternetAvailable()
        }
        assert(states.contains(TvShowViewModel.State.NoInternetAvailable))
    }

    @Test
    fun `when getPrograms is called then should call usecase and emit all states`() {
        val tvShow = mockk<TvShow>()
        every { getTvShowById(1) } answers { flowOf(tvShow) }

        viewModel.getTvShow(1)

        verify(exactly = 1) {
            getTvShowById(1)
        }
        assert(states.contains(TvShowViewModel.State.LoadTvShow(tvShow)))
    }

    @Test
    fun `given exception when getTvShow is called then should call usecase and emit unexpected error`() {
        every { networkHelper.isInternetAvailable() } answers { true }
        every { getTvShowById(1) } answers { flow { throw Exception() } }

        viewModel.getTvShow(1)

        verify(exactly = 1) {
            getTvShowById(1)
        }
        assert(states.contains(TvShowViewModel.State.UnexpectedError))
    }
}
