package com.rehan.jetpackcomposeinmvvm.data_layer

import com.rehan.jetpackcomposeinmvvm.network.MovieAPI
import javax.inject.Inject

// @Inject constructor is used inorder to add the dependency of MovieAPI in this MovieDataSource
class MovieDataSource @Inject constructor(private val movieAPI: MovieAPI) {

    suspend fun getMovieList() = movieAPI.getMovieList(apiKey = "260edb07eae2bfcb51125de8e879570c")

    suspend fun getMovieDetails(id: String) = movieAPI.getMovieDetails(id, apiKey = "260edb07eae2bfcb51125de8e879570c")

}