package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.ui.component.GenresItem
import com.zenbyte.studio.presentation.viewmodel.search.SearchViewModel

@Composable
fun GenresScreen(viewModel: SearchViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(viewModel.getGenresData()) { genre ->
            GenresItem(
                genres = genre
            ){

            }
        }
    }
}