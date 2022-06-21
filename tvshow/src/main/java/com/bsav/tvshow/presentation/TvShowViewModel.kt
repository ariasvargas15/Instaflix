package com.bsav.tvshow.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsav.core.utils.CoroutineContextProvider
import com.bsav.core.utils.NetworkHelper
import com.bsav.tvshow.domain.model.TvShow
import com.bsav.tvshow.domain.usecase.GetTvShowById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val getTvShowById: GetTvShowById,
    private val networkHelper: NetworkHelper,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    fun getTvShow(id: Int) {
        viewModelScope.launch {
            val isThereConnection = checkInternetConnection()
            getTvShowById(id)
                .flowOn(coroutineContextProvider.io)
                .catch {
                    if (isThereConnection) {
                        if (this is Exception) {
                            _state.value = State.UnexpectedError
                            Log.e("getTvShow", message.toString())
                        }
                    }
                }
                .collect {
                    _state.value = State.LoadTvShow(it)
                }
        }
    }

    private fun emitErrorState(flowCollector: FlowCollector<TvShow>) {
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
        data class LoadTvShow(val tvShow: TvShow) : State
    }
}
