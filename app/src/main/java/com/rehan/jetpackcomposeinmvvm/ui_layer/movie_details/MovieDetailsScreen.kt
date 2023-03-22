package com.rehan.jetpackcomposeinmvvm.ui_layer

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rehan.jetpackcomposeinmvvm.ui_layer.movie_details.MovieDetailViewModel

// We have to initialize our ViewModel class with the help of hiltViewModel
@Composable
fun MovieDetailsScreen(movieDetailViewModel: MovieDetailViewModel = hiltViewModel()) {

    val result = movieDetailViewModel.movieDetailsList.value

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

    // Creating UI to display data
    result.data?.let {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {

            // We will be showing Image first inside our UI.
            // As we are using Coil library, we will be using AsyncImage
            // In the model attribute of AsyncImage, we have to pass full url of the image and then append the required image path

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${it.poster_path}",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                contentScale = ContentScale.FillBounds  // It will cover both width and height
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(text = it.original_title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = it.tagline, style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.overview, style = MaterialTheme.typography.body1)
        }
    }

}