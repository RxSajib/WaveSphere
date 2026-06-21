package com.zenbyte.studio.wavesphere.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zenbyte.studio.presentation.viewmodel.favorite.FavoriteChannelViewModel
import com.zenbyte.studio.presentation.viewmodel.search.SearchViewModel
import com.zenbyte.studio.wavesphere.utils.MyCustomLogger

private const val TAG = "FavoriteScreen"
@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    val viewModel : FavoriteChannelViewModel = hiltViewModel()
    val favoriteChannelList = viewModel.favoriteChannel.collectAsStateWithLifecycle(emptyList())


}