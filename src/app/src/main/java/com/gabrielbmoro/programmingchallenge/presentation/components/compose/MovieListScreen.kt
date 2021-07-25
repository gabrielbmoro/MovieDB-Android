package com.gabrielbmoro.programmingchallenge.presentation.components.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.State
import com.gabrielbmoro.programmingchallenge.repository.entities.Movie

@Composable
fun MovieListScreen(moviesState: State<List<Movie>?>, requestMoreElementsCallback: (() -> Unit)) {
    val movies = moviesState.value

    if (movies.isNullOrEmpty()) {
        EmptyState()
    } else {
        val listState = rememberLazyListState()

        if (listState.firstVisibleItemIndex == movies.lastIndex - 2) {
            requestMoreElementsCallback.invoke()
        }

        LazyColumn(state = listState) {
            items(movies) { movie ->
                MovieCard(
                    imageUrl = movie.posterPath,
                    title = movie.title ?: "",
                    releaseDate = movie.releaseDate ?: "",
                    votes = movie.votesAverage ?: 0f
                ) {

                }
            }
        }
    }
}