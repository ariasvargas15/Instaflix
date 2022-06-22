package com.bsav.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.core.coroutinecontext.CoroutineContextProvider
import com.bsav.core.eventwrapper.Event
import com.bsav.home.domain.model.Destination
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.usecase.GetProgramsByType
import com.bsav.home.domain.usecase.navigator.ProgramNavigator
import com.bsav.networkhelper.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProgramsByType: GetProgramsByType,
    private val programNavigator: ProgramNavigator,
    private val networkHelper: NetworkHelper,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state
    private val _navigate = MutableLiveData<Event<Destination>>()
    val navigate: LiveData<Event<Destination>> get() = _navigate

    fun getPrograms() {
        checkInternetConnection()
        getPopularMovies()
        getTopRatedMovies()
        getPopularTvShows()
        getTopRatedTvShows()
    }

    private fun checkInternetConnection() {
        viewModelScope.launch {
            if (!networkHelper.isInternetAvailable()) {
                _state.value = State.NoInternetAvailable
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            getProgramsByType(ProgramType.Movie.Popular)
                .flowOn(coroutineContextProvider.io)
                .catch { _state.value = State.UnexpectedError }
                .collect {
                    _state.value = State.LoadPopularMovies(it)
                }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            getProgramsByType(ProgramType.Movie.TopRated)
                .flowOn(coroutineContextProvider.io)
                .catch { _state.value = State.UnexpectedError }
                .collect {
                    _state.value = State.LoadTopRatedMovies(it)
                }
        }
    }

    private fun getPopularTvShows() {
        viewModelScope.launch {
            getProgramsByType(ProgramType.TvShow.Popular)
                .flowOn(coroutineContextProvider.io)
                .catch { _state.value = State.UnexpectedError }
                .collect {
                    _state.value = State.LoadPopularTvShows(it)
                }
        }
    }

    private fun getTopRatedTvShows() {
        viewModelScope.launch {
            getProgramsByType(ProgramType.TvShow.TopRated)
                .flowOn(coroutineContextProvider.io)
                .catch { _state.value = State.UnexpectedError }
                .collect {
                    _state.value = State.LoadTopRatedTvShows(it)
                }
        }
    }

    fun goToDetail(programId: Int, programType: ProgramType) {
        viewModelScope.launch {
            withContext(coroutineContextProvider.computation) {
                programNavigator.resolveDestination(
                    mapOf("programId" to programId),
                    programType
                )
            }.let { destination ->
                _navigate.value = Event(destination)
            }
        }
    }

    sealed interface State {
        object UnexpectedError : State
        object NoInternetAvailable : State
        data class LoadPopularMovies(val programs: List<Program>) : State
        data class LoadTopRatedMovies(val programs: List<Program>) : State
        data class LoadPopularTvShows(val programs: List<Program>) : State
        data class LoadTopRatedTvShows(val programs: List<Program>) : State
    }
}
