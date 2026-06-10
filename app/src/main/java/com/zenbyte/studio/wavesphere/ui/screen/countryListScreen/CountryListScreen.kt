package com.zenbyte.studio.wavesphere.ui.screen.countryListScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.presentation.viewmodel.countryList.CountryListViewModel
import com.zenbyte.studio.wavesphere.ui.component.CountryItem
import kotlin.collections.lastIndex

@Composable
fun CountryListScreen() {

    val coilContext = LocalPlatformContext.current
    val viewModel : CountryListViewModel = hiltViewModel()
    val countryList = viewModel.countryList.collectAsStateWithLifecycle(emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(countryList.value.size){position ->
            CountryItem(context = coilContext, country = countryList.value[position])
            if (position < countryList.value.size) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            }
        }
    }
}