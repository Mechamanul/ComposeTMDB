package com.mechamanul.composetmdb.ui

sealed class Screen(val route: String) {
    object PopularMovies : Screen(route = "popular_movies")

}
