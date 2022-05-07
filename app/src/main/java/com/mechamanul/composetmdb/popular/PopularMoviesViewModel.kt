package com.mechamanul.composetmdb.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.composetmdb.domain.usecase.GetPopularMoviesUseCase
import com.mechamanul.utils.composetmdb.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val popularMovies: GetPopularMoviesUseCase) :
    ViewModel() {
    init {
        fetchMovies()
    }

    private val _uiState = MutableStateFlow<PopularMoviesUiState>(PopularMoviesUiState.Empty)
    val uiState: StateFlow<PopularMoviesUiState> = _uiState

    sealed class PopularMoviesUiState {
        data class Success(val moviesList: List<Movie>) : PopularMoviesUiState()
        data class Error(val throwable: Throwable) : PopularMoviesUiState()
        object Loading : PopularMoviesUiState()
        object Empty : PopularMoviesUiState()
    }

    private fun fetchMovies() = viewModelScope.launch {
//        _uiState.value = PopularMoviesUiState.Loading
        popularMovies.invoke().collect {
            when (it) {
                is Result.Error -> _uiState.value = PopularMoviesUiState.Error(it.throwable)
                Result.Loading -> _uiState.value = PopularMoviesUiState.Loading
                is Result.Success -> _uiState.value = PopularMoviesUiState.Success(it.data)
            }
        }
    }


}