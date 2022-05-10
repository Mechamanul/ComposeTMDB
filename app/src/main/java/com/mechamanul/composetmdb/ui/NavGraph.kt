package com.mechamanul.composetmdb.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mechamanul.composetmdb.ui.details.composables.MovieDetailsScreen
import com.mechamanul.composetmdb.ui.popular.composables.PopularMoviesScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.PopularMovies.route) {
        composable(route = Screen.PopularMovies.route) {

            PopularMoviesScreen(viewModel = hiltViewModel(), onMovieSelect = { movieId: String ->
                navController.navigate(Screen.MovieDetails.get(movieId))
            })
        }
        composable(
            route = Screen.MovieDetails.route, arguments = listOf(navArgument(
                MOVIE_DETAILS_ARG
            ) {
                type = NavType.StringType
            })
        ) {

            MovieDetailsScreen(viewModel = hiltViewModel())
        }
    }

}