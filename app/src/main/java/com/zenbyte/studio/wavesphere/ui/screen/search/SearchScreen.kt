package com.zenbyte.studio.wavesphere.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.presentation.viewmodel.search.SearchViewModel
import com.zenbyte.studio.wavesphere.ui.component.CountryItem

@Composable
fun SearchScreen() {

    val context = LocalPlatformContext.current
    val viewModel : SearchViewModel = hiltViewModel()
    val countryList = viewModel.countryList.collectAsStateWithLifecycle(emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(countryList.value){country ->
            CountryItem(context = context, country = country){

            }
        }
    }
}