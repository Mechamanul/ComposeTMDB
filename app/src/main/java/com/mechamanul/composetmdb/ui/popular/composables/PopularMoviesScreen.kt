package com.mechamanul.composetmdb.ui.popular.composables


import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.composetmdb.ui.popular.PopularMoviesViewModel

@Composable
fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel(),
    onMovieSelect: (String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        PopularMoviesViewModel.PopularMoviesUiState.Empty -> Text(text = "Empty for now")
        is PopularMoviesViewModel.PopularMoviesUiState.Error -> Text(text = uiState.throwable.message.toString())
        PopularMoviesViewModel.PopularMoviesUiState.Loading -> Text(text = "Loading...")
        is PopularMoviesViewModel.PopularMoviesUiState.Success -> PopularMoviesScreenContent(
            moviesList = uiState.moviesList, onMovieSelect = onMovieSelect
        )
    }
}

@Composable
fun PopularMoviesScreenContent(moviesList: List<Movie>, onMovieSelect: (String) -> Unit) {
    LazyColumn {
        items(moviesList) { item: Movie ->
            MovieListItem(modifier = Modifier.clickable { onMovieSelect(item.id) }, movie = item)
        }
    }
}

