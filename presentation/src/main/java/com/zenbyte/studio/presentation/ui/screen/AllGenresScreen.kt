package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.GenresItem
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.navigation.AppDestination
import com.zenbyte.studio.presentation.viewmodel.genres.GenresViewModel
import com.zenbyte.studio.presentation.viewmodel.search.SearchViewModel

@Composable
fun AllGenresScreen(rootBackStack: NavBackStack<NavKey>) {

    val viewModel : GenresViewModel = hiltViewModel()

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {

        Scaffold(
            topBar = {
                MyCustomAppBar(title = stringResource(R.string.genres)) {

                }
            }
        ) { innerPadding ->
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding()),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(viewModel.getGenresData()) { genre ->
                    GenresItem(
                        genres = genre
                    ){genres ->
                        rootBackStack.add(
                            AppDestination.Dest(
                                firstDestName = AppDestination.Dest.ChannelByGenres::class.simpleName.orEmpty(),
                                genres = genres
                            )
                        )
                    }
                }
            }
        }

    }
}