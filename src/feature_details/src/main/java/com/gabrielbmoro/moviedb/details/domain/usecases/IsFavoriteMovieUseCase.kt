package com.gabrielbmoro.moviedb.details.domain.usecases

import com.gabrielbmoro.moviedb.domain.model.DataOrException
import com.gabrielbmoro.moviedb.repository.MoviesRepository

interface IsFavoriteMovieUseCase {
    suspend operator fun invoke(movieTitle: String): DataOrException<Boolean, Exception>
}

class IsFavoriteMovieUseCaseImpl(
    private val repository: MoviesRepository
) : IsFavoriteMovieUseCase {

    override suspend operator fun invoke(movieTitle: String): DataOrException<Boolean, Exception> {
        return repository.checkIsAFavoriteMovie(movieTitle)
    }
}
