package com.rehan.jetpackcomposeinmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rehan.jetpackcomposeinmvvm.ui_layer.MovieDetailsScreen
import com.rehan.jetpackcomposeinmvvm.ui_layer.MovieListScreen

@Composable
fun MovieNavigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = MovieNavigationItem.MovieList.route
    ) {

        // First screen
        composable(MovieNavigationItem.MovieList.route) {
            MovieListScreen(navHostController)
        }

        // Second screen
        composable(MovieNavigationItem.MovieDetails.route+"/{id}") {
            MovieDetailsScreen()
        }
    }
}