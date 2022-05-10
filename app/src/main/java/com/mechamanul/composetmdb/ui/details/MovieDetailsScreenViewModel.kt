package com.mechamanul.composetmdb.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mechamanul.composetmdb.domain.models.MovieDetails
import com.mechamanul.composetmdb.domain.usecase.GetMovieDetailsUseCase
import com.mechamanul.utils.composetmdb.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsScreenViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _uiState = MutableStateFlow<DetailsScreenUiState>(DetailsScreenUiState.Empty)
    val uiState: StateFlow<DetailsScreenUiState> = _uiState

    sealed class DetailsScreenUiState {
        object Empty : DetailsScreenUiState()
        object Loading : DetailsScreenUiState()
        data class Error(val throwable: Throwable) : DetailsScreenUiState()
        data class Success(val movieDetails: MovieDetails) : DetailsScreenUiState()
    }

    init {
        viewModelScope.launch {
            getMovieDetails()
        }
    }

    private fun getMovieDetails() = viewModelScope.launch {
        savedStateHandle.get<String>("id")?.let {
            Log.d("insideMovieDetails", "id: ${it}")
            Log.d("insideMovieDetails", "Here")
            getMovieDetailsUseCase.invoke(it).collect {

                when (it) {
                    is Result.Error ->
                        _uiState.value = DetailsScreenUiState.Error(it.throwable)
                    is Result.Loading -> _uiState.value = DetailsScreenUiState.Loading
                    is Result.Success -> _uiState.value = DetailsScreenUiState.Success(it.data)
                }
            }
        }
    }

}