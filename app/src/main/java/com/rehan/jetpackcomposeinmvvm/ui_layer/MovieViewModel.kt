package com.rehan.jetpackcomposeinmvvm.ui_layer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rehan.jetpackcomposeinmvvm.common.Resource
import com.rehan.jetpackcomposeinmvvm.data_layer.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    // Inorder to store success state /error state in UI elements, we use one variable in this ViewModel class
     val movieList = mutableStateOf(MovieStateHolder())

    // As soon as ViewModel gets initialized, first we will show loading and then we will show data from server
    init {
        movieList.value = MovieStateHolder(isLoading = true)
        getMovieList()
    }

    // Handling states in background thread of coroutines
    private fun getMovieList() = viewModelScope.launch(Dispatchers.IO) {

        val result = movieRepository.getMovieList()

        when (result) {
            is Resource.Success -> {        // This is success state
                movieList.value = MovieStateHolder(data = result.data)
            }
            is Resource.Error -> {          // This is error state
                movieList.value = MovieStateHolder(error = result.message.toString())
            }
            else -> {

            }
        }
    }
}