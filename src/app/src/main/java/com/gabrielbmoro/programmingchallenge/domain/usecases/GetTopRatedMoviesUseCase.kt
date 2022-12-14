package com.gabrielbmoro.programmingchallenge.domain.usecases

import com.gabrielbmoro.programmingchallenge.domain.model.DataOrException
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository
import com.gabrielbmoro.programmingchallenge.domain.model.Page
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository,
) {

    suspend operator fun invoke(pageNumber: Int): DataOrException<Page, Exception> {
        return withContext(Dispatchers.IO) {
            repository.getTopRatedMovies(pageNumber)
        }
    }
}