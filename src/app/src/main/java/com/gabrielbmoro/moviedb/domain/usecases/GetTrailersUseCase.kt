package com.gabrielbmoro.moviedb.domain.usecases

import com.gabrielbmoro.moviedb.domain.model.DataOrException
import com.gabrielbmoro.moviedb.domain.model.VideoStream
import com.gabrielbmoro.moviedb.repository.MoviesRepository

class GetTrailersUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Long): DataOrException<VideoStream?, Exception> {
        val result = repository.getVideoStreams(movieId)
        val trailer = result.data?.firstOrNull {
            it.site == "YouTube" && it.official && it.type == "Trailer"
        }

        return DataOrException(trailer, result.exception)
    }

    companion object {
        private const val SITE_KEY = "YouTube"
        private const val TYPE_KEY = "Trailer"
    }
}