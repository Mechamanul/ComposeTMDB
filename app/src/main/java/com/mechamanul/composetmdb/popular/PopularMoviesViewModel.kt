package com.mechamanul.composetmdb.popular

import androidx.lifecycle.ViewModel
import com.mechamanul.composetmdb.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val popularMovies: GetPopularMoviesUseCase):ViewModel() {


}