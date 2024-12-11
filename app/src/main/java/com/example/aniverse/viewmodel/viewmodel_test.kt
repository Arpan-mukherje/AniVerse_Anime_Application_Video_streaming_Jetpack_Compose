package com.example.aniverse.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
@HiltViewModel
class TestViewModel @Inject constructor() : ViewModel() {
   var listIs= listOf("1","2","3","4")
var list2:List<String> = listIs;
}