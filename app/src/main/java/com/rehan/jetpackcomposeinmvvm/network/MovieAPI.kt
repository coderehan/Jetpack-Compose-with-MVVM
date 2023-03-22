package com.rehan.jetpackcomposeinmvvm.network

import com.rehan.jetpackcomposeinmvvm.models.MovieListResponse
import com.rehan.jetpackcomposeinmvvm.models.movie_details.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("3/movie/popular")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String
    ): MovieListResponse

    @GET("3/movie/{id}")    // {id} is dynamic id of movie here
    suspend fun getMovieDetails(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): MovieDetails

}