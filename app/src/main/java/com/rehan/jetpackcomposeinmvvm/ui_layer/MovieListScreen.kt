package com.rehan.jetpackcomposeinmvvm.ui_layer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.rehan.jetpackcomposeinmvvm.models.Movie
import com.rehan.jetpackcomposeinmvvm.navigation.MovieNavigation
import com.rehan.jetpackcomposeinmvvm.navigation.MovieNavigationItem

// We have to initialize our ViewModel class with the help of hiltViewModel
@Composable
fun MovieListScreen(
    navController: NavController,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    val result = movieViewModel.movieList.value

    // Creating progress bar UI
    if (result.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    // Creating error message text UI
    if (result.error.isNotBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = result.error,
                style = MaterialTheme.typography.h6
            )
        }
    }

    // Creating LazyColumn UI to display data
    result.data?.let {
        LazyColumn {
            items(result.data) {
                // Separate UI for LazyColumn
                MovieItem(it) {
                    navController.navigate(MovieNavigationItem.MovieDetails.route+"/${it}")
                }
            }
        }
    }

}

@Composable
fun MovieItem(it: Movie, onClick: (String) -> Unit) {
    // We will be showing Image first inside LazyColumn.
    // As we are using Coil library, we will be using AsyncImage
    // In the model attribute of AsyncImage, we have to pass full url of the image and then append the required image path
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500/${it.poster_path}",
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
            .padding(vertical = 4.dp)
            .clickable {
                onClick.invoke(it.id.toString())
            },
        contentScale = ContentScale.Crop
    )
}
