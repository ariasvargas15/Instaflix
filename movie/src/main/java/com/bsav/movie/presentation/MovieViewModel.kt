package com.bsav.movie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.core.coroutinecontext.CoroutineContextProvider
import com.bsav.movie.domain.model.Movie
import com.bsav.movie.domain.usecase.GetMovieById
import com.bsav.networkhelper.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieById: GetMovieById,
    private val networkHelper: NetworkHelper,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    fun getMovie(id: Int) {
        viewModelScope.launch {
            val isThereConnection = checkInternetConnection()
            getMovieById(id)
                .flowOn(coroutineContextProvider.io)
                .catch {
                    if (isThereConnection) {
                        _state.value = State.UnexpectedError
                    }
                }
                .collect {
                    _state.value = State.LoadMovie(it)
                }
        }
    }

    private fun checkInternetConnection(): Boolean {
        return networkHelper.isInternetAvailable().also {
            if (!it) {
                _state.value = State.NoInternetAvailable
            }
        }
    }

    sealed interface State {
        object UnexpectedError : State
        object NoInternetAvailable : State
        data class LoadMovie(val movie: Movie) : State
    }
}
