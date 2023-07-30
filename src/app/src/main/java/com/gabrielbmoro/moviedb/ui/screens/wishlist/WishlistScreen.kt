package com.gabrielbmoro.moviedb.ui.screens.wishlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gabrielbmoro.moviedb.R
import com.gabrielbmoro.moviedb.domain.model.Movie
import com.gabrielbmoro.moviedb.ui.common.navigation.NavigationItem
import com.gabrielbmoro.moviedb.ui.common.widgets.BubbleLoader
import com.gabrielbmoro.moviedb.ui.common.widgets.EmptyState
import com.gabrielbmoro.moviedb.ui.common.widgets.MovieList
import com.gabrielbmoro.moviedb.ui.common.widgets.ScreenScaffold
import kotlinx.coroutines.launch

@Composable
fun WishlistScreen(
    navController: NavController,
    viewModel: WishlistViewModel,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    val onSelectMovie: ((Movie) -> Unit) = { movie ->
        navController.navigate(
            NavigationItem.DetailsScreen(movie).route
        )
    }

    ScreenScaffold(
        areBarsVisible = uiState.value.areBarsVisible,
        appBarTitle = stringResource(id = R.string.favorite_movies_screen_title),
        navController = navController,
        updateScrollPosition = {
            viewModel.updateScrollPosition(it.y)
        },
        scrollToTop = {
            coroutineScope.launch {
                lazyListState.scrollToItem(0, 0)
            }
        }
    ) {
        when {
            uiState.value.isLoading -> {
                BubbleLoader(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }

            uiState.value.favoriteMovies?.isEmpty() == true -> {
                EmptyState(
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }

            else -> {
                uiState.value.favoriteMovies?.let { moviesList ->
                    MovieList(
                        moviesList = moviesList,
                        onSelectMovie = onSelectMovie,
                        lazyListState = lazyListState,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}