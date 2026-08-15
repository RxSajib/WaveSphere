package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.MyCustomInputFiled
import com.zenbyte.studio.presentation.ui.component.MyCustomMenuGroup
import com.zenbyte.studio.presentation.viewmodel.search.SearchViewModel

@Composable
fun SearchScreen(activeBackStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel: SearchViewModel = hiltViewModel()
    val countryList = viewModel.countryState.collectAsStateWithLifecycle()
    val searchData = viewModel.searchInput.collectAsStateWithLifecycle()
    val selectedMenuPosition = viewModel.selectedMenuPosition.collectAsStateWithLifecycle()
    val newsList = viewModel.newsList.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        HeightGap(height = 10.dp)
        MyCustomInputFiled(
            placeHolderText = stringResource(R.string.search_input_hilt),
            text = searchData.value,
            onValueChange = { searchTag ->
                viewModel.inputSearchData(searchTag)
            },
            modifier = Modifier.padding(horizontal = 16.dp),
            isSearchEnable = true,
            isPasswordVisibility = true,
            rightIcon = painterResource(R.drawable.icon_search),
            onSearch = {

            }
        ) {}

        HeightGap(height = 20.dp)
        MyCustomMenuGroup(
            viewModel = viewModel,
            modifier = Modifier.padding(horizontal = 16.dp),
            onClickCountries = {},
            onClickGenres = {},
            onClickNews = {}
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            if (selectedMenuPosition.value == 1) {
                CountriesScreen(rootBackStack = rootBackStack, viewModel = viewModel)
            } else if (selectedMenuPosition.value == 2) {
                GenresScreen(rootBackStack = rootBackStack, viewModel = viewModel)
            } else if (selectedMenuPosition.value == 3) {
                NewsChannelListScreen(newsList = newsList, viewModel = viewModel)
            }
        }
    }

}