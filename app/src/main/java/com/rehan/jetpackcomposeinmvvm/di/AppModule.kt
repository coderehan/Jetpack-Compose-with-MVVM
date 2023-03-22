package com.rehan.jetpackcomposeinmvvm.di

import com.rehan.jetpackcomposeinmvvm.data_layer.MovieDataSource
import com.rehan.jetpackcomposeinmvvm.data_layer.MovieRepository
import com.rehan.jetpackcomposeinmvvm.network.MovieAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieAPI {
        return retrofit.create(MovieAPI::class.java)
    }

    @Provides
    fun provideMovieDataSource(movieAPI: MovieAPI): MovieDataSource {
        return MovieDataSource(movieAPI)
    }

    @Provides
    fun provideMovieRepository(movieDataSource: MovieDataSource): MovieRepository {
        return MovieRepository(movieDataSource)
    }
}
