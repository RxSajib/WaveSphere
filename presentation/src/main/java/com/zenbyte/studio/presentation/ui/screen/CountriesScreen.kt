package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.CountryItem
import com.zenbyte.studio.presentation.ui.component.CountryShimmer
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.ServerError
import com.zenbyte.studio.presentation.ui.navigation.AppDestination
import com.zenbyte.studio.presentation.viewmodel.search.SearchViewModel

@Composable
fun CountriesScreen(rootBackStack: NavBackStack<NavKey>) {

    val context = LocalPlatformContext.current
    val viewModel : SearchViewModel = hiltViewModel()
    val countryList by viewModel.countryState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MyCustomAppBar(title = "All Country", isBackButtonEnable = false, isPremiumEnable = false) { }
        }
    ) {innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
            if (countryList.isSuccess) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items = countryList.data ?: emptyList()) { country ->
                        CountryItem(context = context, country = country) {
                            rootBackStack.add(AppDestination.Dest.ChannelByCountry(countryName = it.name))
                        }
                    }
                }
            } else if (countryList.isLoading) {
                CountryShimmer()
            } else {
                ServerError {
                    viewModel.getAllCountry()
                }
            }
        }
    }




}