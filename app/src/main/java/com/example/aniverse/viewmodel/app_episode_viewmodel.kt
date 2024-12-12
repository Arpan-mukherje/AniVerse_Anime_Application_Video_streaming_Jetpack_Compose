package com.example.aniverse.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aniverse.apiservices.ApiService
import com.example.aniverse.model.AnimeAllEpisodeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AllEpisodeViewModel @Inject constructor(private val apiService: ApiService) : ViewModel(){
    private  var _getAllEpisode = MutableStateFlow<List<AnimeAllEpisodeModel.Data>>(emptyList())
    var getAllEpisode:StateFlow<List<AnimeAllEpisodeModel.Data>> = _getAllEpisode

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _currentPage = MutableStateFlow(1)
    private var hasNextPage = true


    fun fetchAllEpisode(episodeId: Int) {
        if (_isLoading.value || !hasNextPage) return
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response=apiService.getAllEpisode(episodeId,_currentPage.value)
                Log.d("AllEpisodeViewModel-> ", "Response: $response")
                val updatedList = _getAllEpisode.value + response.data.reversed()
                _getAllEpisode.value = updatedList
                hasNextPage = response.pagination.has_next_page
                if (hasNextPage) _currentPage.value += 1
            }catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown Error"
                Log.e("AllEpisodeViewModel", "Error fetching AllEpisode anime", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}