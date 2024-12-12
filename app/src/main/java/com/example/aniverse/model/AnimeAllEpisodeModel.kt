package com.example.aniverse.model

data class AnimeAllEpisodeModel(
    val `data`: List<Data>,
    val pagination: Pagination
) {
    data class Data(
        val episode: String,
        val images: Images,
        val mal_id: Int,
        val title: String,
        val url: String
    ) {
        data class Images(
            val jpg: Jpg
        ) {
            data class Jpg(
                val image_url: String
            )
        }
    }

    data class Pagination(
        val has_next_page: Boolean,
        val last_visible_page: Int
    )
}