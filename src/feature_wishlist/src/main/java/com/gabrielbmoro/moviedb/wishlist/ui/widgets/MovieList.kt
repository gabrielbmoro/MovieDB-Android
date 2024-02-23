package com.gabrielbmoro.moviedb.wishlist.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gabrielbmoro.moviedb.core.ui.widgets.MovieCard
import com.gabrielbmoro.moviedb.repository.model.Movie

@Composable
fun MovieList(
    moviesList: List<Movie>,
    onSelectMovie: (Movie) -> Unit,
    onDeleteMovie: (Movie) -> Unit,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            count = moviesList.size,
            key = { index ->
                moviesList[index].id
            }
        ) { index ->

            moviesList[index].let { movie ->
                MovieCard(
                    imageUrl = movie.posterImageUrl,
                    title = movie.title,
                    votes = movie.votesAverage,
                    description = movie.overview,
                    onClick = { onSelectMovie(movie) },
                    enableSwipeToDelete = true,
                    onDeleteClick = { onDeleteMovie(movie) }
                )
            }
        }
    }
}
