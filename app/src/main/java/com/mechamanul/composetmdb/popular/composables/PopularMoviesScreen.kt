package com.mechamanul.composetmdb.popular.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.mechamanul.composetmdb.popular.PopularMoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun PopularMoviesScreen(viewModel: PopularMoviesViewModel = hiltViewModel()){

}