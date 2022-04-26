package com.mechamanul.composetmdb.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.composetmdb.domain.usecase.GetPopularMoviesUseCase
import com.mechamanul.utils.composetmdb.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val popularMovies: GetPopularMoviesUseCase):ViewModel() {
    private val _moviesList:MutableStateFlow<Result<List<Movie>>> = MutableStateFlow(Result.Loading)
    val moviesList: StateFlow<Result<List<Movie>>> = _moviesList
    init {
        viewModelScope.launch {

            popularMovies.invoke().collect {
                when(it){
                    is Result.Error -> it.throwable
                    is Result.Success -> _moviesList.value=it
                    Result.Loading -> it
                }
            }
        }
    }

}