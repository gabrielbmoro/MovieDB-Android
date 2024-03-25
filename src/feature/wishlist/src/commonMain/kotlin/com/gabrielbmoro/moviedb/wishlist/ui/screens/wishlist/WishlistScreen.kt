package com.gabrielbmoro.moviedb.wishlist.ui.screens.wishlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.gabrielbmoro.moviedb.desingsystem.images.EmptyState
import com.gabrielbmoro.moviedb.desingsystem.loaders.BubbleLoader
import com.gabrielbmoro.moviedb.desingsystem.scaffold.ScreenScaffold
import com.gabrielbmoro.moviedb.desingsystem.toolbars.FavoriteTabIndex
import com.gabrielbmoro.moviedb.desingsystem.toolbars.NavigationBottomBar
import com.gabrielbmoro.moviedb.wishlist.ui.widgets.MovieList
import kotlinx.coroutines.launch
import moviedb_android.feature.wishlist.generated.resources.Res
import moviedb_android.feature.wishlist.generated.resources.delete_fail_message
import moviedb_android.feature.wishlist.generated.resources.delete_success_message
import moviedb_android.feature.wishlist.generated.resources.wishlist
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
class WishlistScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<WishlistScreenModel>()
        val uiState = viewModel.uiState.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }
        val lazyListState = rememberLazyListState()

        val successDeleteMessage = stringResource(Res.string.delete_success_message)
        val errorDeleteMessage = stringResource(Res.string.delete_fail_message)

        val navigator = LocalNavigator.currentOrThrow

        val coroutineScope = rememberCoroutineScope()

        ScreenScaffold(
            showTopBar = true,
            appBarTitle = stringResource(Res.string.wishlist),
            bottomBar = {
                NavigationBottomBar(
                    currentTabIndex = FavoriteTabIndex,
                    onSelectFavoriteTab = {
                        coroutineScope.launch {
                            lazyListState.scrollToItem(0)
                        }
                    },
                    onSelectMoviesTab = {
                        /*navigator.push(
                            navDestinations.moviesScreen()
                        )*/
                    }
                )
            },
            snackBarHost = {
                SnackbarHost(snackbarHostState)
            }
        ) {
            when {
                uiState.value.isLoading -> {
                    BubbleLoader(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.value.favoriteMovies?.isEmpty() == true -> {
                    EmptyState(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    if (uiState.value.favoriteMovies != null) {
                        MovieList(
                            moviesList = uiState.value.favoriteMovies!!,
                            onSelectMovie = {
                                /*navigator.push(
                                    navDestinations.detailsScreen(it)
                                )*/
                            },
                            lazyListState = lazyListState,
                            onDeleteMovie = { movie ->
                                viewModel.accept(WishlistUserIntent.DeleteMovie(movie))
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.TopCenter)
                        )
                    }
                }
            }
        }

        LaunchedEffect(
            key1 = uiState.value,
            block = {
                if (uiState.value.isSuccessResult != null) {
                    val resultMessage = if (uiState.value.isSuccessResult == true) {
                        successDeleteMessage
                    } else {
                        errorDeleteMessage
                    }
                    snackbarHostState.showSnackbar(resultMessage)
                    viewModel.accept(WishlistUserIntent.ResultMessageReset)
                    viewModel.accept(WishlistUserIntent.LoadMovies)
                }
            }
        )
    }

}