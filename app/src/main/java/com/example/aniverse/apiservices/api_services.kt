package com.example.aniverse.apiservices

import com.example.aniverse.model.AnimeAllEpisodeModel
import com.example.aniverse.model.TopAnimeResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //--------------get all top anime---------------
    @GET("top/anime")
    suspend fun getPosts(
        @Query("page") nextpage: Int
    ): TopAnimeResponseModel

    //---------------get all episode for a anime---------------------


    @GET("anime/{id}/videos/episodes")
    suspend fun getAllEpisode(
        @Path("id") episodeId: Int,
        @Query("page") nextpage: Int

    ): AnimeAllEpisodeModel


}
