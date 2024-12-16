package com.example.aniverse.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aniverse.apiservices.ApiService
import com.example.aniverse.model.TopAnimeResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class AnimeViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _topAnime = MutableStateFlow<List<TopAnimeResponseModel.Data>>(emptyList())
    val topAnime: StateFlow<List<TopAnimeResponseModel.Data>> = _topAnime

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _currentPage = MutableStateFlow(1)
    private var hasNextPage = true

//    init {
//        fetchTopAnime()
//    }

    fun fetchTopAnime() {
        if (_isLoading.value || !hasNextPage) return

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = apiService.getPosts(_currentPage.value)
                val newList = _topAnime.value?.plus(response.data) ?: response.data
                _topAnime.value = newList
                hasNextPage = response.pagination.has_next_page
                if (hasNextPage) _currentPage.value += 1
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown Error"
                Log.e("AnimeViewModel", "Error fetching top anime", e)
            } finally {
                _isLoading.value = false
            }
        }}
    }

