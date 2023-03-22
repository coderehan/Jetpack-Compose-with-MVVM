package com.rehan.jetpackcomposeinmvvm.ui_layer.movie_details

import com.rehan.jetpackcomposeinmvvm.models.movie_details.MovieDetails

data class MovieDetailsStateHolder(
    val isLoading: Boolean = false,
    val data: MovieDetails? = null,
    val error: String = ""
)
