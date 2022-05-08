package com.mechamanul.composetmdb.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mechamanul.composetmdb.ui.popular.composables.PopularMoviesScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.PopularMovies.route) {
        composable(Screen.PopularMovies.route) {
            PopularMoviesScreen()
        }
    }

}