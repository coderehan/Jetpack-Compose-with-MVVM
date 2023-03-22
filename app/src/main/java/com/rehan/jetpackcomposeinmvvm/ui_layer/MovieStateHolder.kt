package com.rehan.jetpackcomposeinmvvm.ui_layer

import com.rehan.jetpackcomposeinmvvm.models.Movie

data class MovieStateHolder(
    val isLoading: Boolean = false,
    val data: List<Movie>? = null,
    val error: String = ""
)
