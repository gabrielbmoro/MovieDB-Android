package com.gabrielbmoro.moviedb.details.ui.screens.details

data class DetailsUIState(
    val movieTitle: String,
    val isLoading: Boolean = false,
    val isFavorite: Boolean,
    val movieVotesAverage: Float,
    val movieLanguage: String,
    val moviePopularity: Float,
    val movieOverview: String,
    val imageUrl: String?,
    val tagLine: String? = null,
    val genres: String? = null,
    val status: String? = null,
    val productionCompanies: String? = null,
    val homepage: String? = null,
    val videoId: String? = null,
    val showVideo: Boolean = true,
    val errorMessage: String? = null
) {
    companion object {
        fun empty() = DetailsUIState(
            isFavorite = false,
            movieTitle = "",
            moviePopularity = 0f,
            movieLanguage = "",
            movieVotesAverage = 0f,
            movieOverview = "",
            imageUrl = ""
        )

        fun mocked1() = DetailsUIState(
            isFavorite = true,
            movieTitle = "Dragão branco",
            moviePopularity = 2f,
            movieLanguage = "pt-BR",
            movieVotesAverage = 5f,
            movieOverview = "Um filme que conta a história de um lutador de karatê",
            imageUrl = ""
        )
    }
}
