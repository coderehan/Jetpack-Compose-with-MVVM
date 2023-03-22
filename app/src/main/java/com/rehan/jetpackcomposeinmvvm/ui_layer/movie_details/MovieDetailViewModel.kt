package com.rehan.jetpackcomposeinmvvm.ui_layer.movie_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rehan.jetpackcomposeinmvvm.common.Resource
import com.rehan.jetpackcomposeinmvvm.data_layer.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
// With the help of savedStateHandle, we can get the id from startDestination screen
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Inorder to store success state /error state in UI elements, we use one variable in this ViewModel class
    val movieDetailsList = mutableStateOf(MovieDetailsStateHolder())

    // As soon as ViewModel gets initialized, first we will show loading and then we will show data from server
    init {
        movieDetailsList.value = MovieDetailsStateHolder(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            savedStateHandle.getStateFlow("id", "0").collectLatest {
                getMovieDetails(it)
            }
        }
    }

    // Handling states in background thread of coroutines
    fun getMovieDetails(id: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = movieRepository.getMovieDetails(id)

        when (result) {
            is Resource.Success -> {
                movieDetailsList.value = MovieDetailsStateHolder(data = result.data)
            }
            is Resource.Error -> {
                movieDetailsList.value = MovieDetailsStateHolder(error = result.message.toString())
            }
            else -> {

            }
        }
    }


}