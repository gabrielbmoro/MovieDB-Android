package com.gabrielbmoro.moviedb.wishlist.domain.usecases

import com.gabrielbmoro.moviedb.repository.MoviesRepository
import com.gabrielbmoro.moviedb.repository.model.Movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetFavoriteMoviesUseCaseTest {

    private lateinit var repository: MoviesRepository
    private lateinit var useCase: GetFavoriteMoviesUseCase

    @Before
    fun before() {
        repository = mockk()
        useCase = GetFavoriteMoviesUseCase(repository)
    }

    @Test
    fun `should be able to get all favorite movies`() {
        // arrange
        val favoriteMovies = listOf(Movie.mockWhiteDragonNotFavorite())

        every { repository.getFavoriteMovies() }.returns(flowOf(favoriteMovies))

        runTest {
            // act
            useCase().collect {
                // assert
                verify(exactly = 1) { repository.getFavoriteMovies() }
            }
        }
    }
}
