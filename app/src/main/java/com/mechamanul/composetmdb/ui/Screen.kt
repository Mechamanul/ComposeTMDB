package com.mechamanul.composetmdb.ui

const val MOVIE_DETAILS_ARG = "id"

sealed class Screen(val route: String) {
    object PopularMovies : Screen(route = "popular_movies")
    object MovieDetails : Screen(route = "movie_details/{$MOVIE_DETAILS_ARG}") {
        fun get(id: String): String {
            return this.route.replace("{$MOVIE_DETAILS_ARG}", id)
        }
    }
}
