//package com.example.aniverse.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//import kotlin.time.Duration
//@HiltViewModel
//class SplashScreenViewModel @Inject constructor() : ViewModel() {
//    private val _splashTimeOut = MutableStateFlow(false)
//    val splashTimeOut: StateFlow<Boolean> = _splashTimeOut
//
//    init {
//        viewModelScope.launch {
//            delay(4000)
//            _splashTimeOut.value = true
//        }
//    }
//}
