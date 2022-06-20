package com.bsav.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.core.utils.CoroutineContextProvider
import com.bsav.home.domain.model.Destination
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.usecase.GetProgramsByType
import com.bsav.home.domain.usecase.navigator.ProgramNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProgramsByType: GetProgramsByType,
    private val programNavigator: ProgramNavigator,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    fun getPrograms() {
        getPopularMovies()
        getTopRatedMovies()
        getPopularTvShows()
        getTopRatedTvShows()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            getProgramsByType(ProgramType.Movie.Popular)
                .flowOn(coroutineContextProvider.io)
                .catch { _state.value = State.Error }
                .collect {
                    _state.value = State.LoadPopularMovies(it)
                }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            getProgramsByType(ProgramType.Movie.TopRated)
                .flowOn(coroutineContextProvider.io)
                .catch { _state.value = State.Error }
                .collect {
                    _state.value = State.LoadTopRatedMovies(it)
                }
        }
    }

    private fun getPopularTvShows() {
        viewModelScope.launch {
            getProgramsByType(ProgramType.TvShow.Popular)
                .flowOn(coroutineContextProvider.io)
                .catch { _state.value = State.Error }
                .collect {
                    _state.value = State.LoadPopularTvShows(it)
                }
        }
    }

    private fun getTopRatedTvShows() {
        viewModelScope.launch {
            getProgramsByType(ProgramType.TvShow.TopRated)
                .flowOn(coroutineContextProvider.io)
                .catch { _state.value = State.Error }
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
                _state.value = State.NavigateTo(destination)
            }
        }
    }

    sealed interface State {
        object Error : State
        data class LoadPopularMovies(val programs: List<Program>) : State
        data class LoadTopRatedMovies(val programs: List<Program>) : State
        data class LoadPopularTvShows(val programs: List<Program>) : State
        data class LoadTopRatedTvShows(val programs: List<Program>) : State
        data class NavigateTo(val destination: Destination) : State
    }
}
