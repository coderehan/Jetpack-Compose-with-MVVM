package com.rehan.jetpackcomposeinmvvm.data_layer

import com.rehan.jetpackcomposeinmvvm.common.Resource
import com.rehan.jetpackcomposeinmvvm.models.Movie
import com.rehan.jetpackcomposeinmvvm.models.movie_details.MovieDetails
import javax.inject.Inject

// @Inject constructor is used inorder to add the dependency of MovieDataSource in this repository
class MovieRepository @Inject constructor(private val movieDataSource: MovieDataSource) {

    suspend fun getMovieList(): Resource<List<Movie>> {
        // Whenever we make network call to server, there is always possibility that we can get error also. We use try/catch block to catch that error
        return try {
            Resource.Success(data = movieDataSource.getMovieList().results)        // Handling success data when we get data from server successfully
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())  // Handling error data when we don't get data from server
        }
    }

    suspend fun getMovieDetails(id: String): Resource<MovieDetails> {
        return try {
            Resource.Success(data = movieDataSource.getMovieDetails(id))
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }
}