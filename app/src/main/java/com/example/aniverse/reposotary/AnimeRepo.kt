//package com.example.aniverse.reposotary
//
//import com.example.aniverse.apiservices.ApiService
//import com.example.aniverse.model.TopAnimeResponseModel
//import javax.inject.Inject
//
//class AnimeRepository @Inject constructor(private val apiService: ApiService) {
//    suspend fun getTopAnime(nextpage:Int): TopAnimeResponseModel {
//        return apiService.getPosts(nextpage = nextpage)
//    }
//}
