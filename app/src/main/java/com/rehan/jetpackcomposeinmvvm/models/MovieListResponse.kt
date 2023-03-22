package com.rehan.jetpackcomposeinmvvm.models

data class MovieListResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)