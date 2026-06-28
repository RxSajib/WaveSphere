package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.presentation.ui.component.CountryItem
import com.zenbyte.studio.presentation.ui.component.CountryShimmer
import com.zenbyte.studio.presentation.ui.component.ServerError
import com.zenbyte.studio.presentation.viewmodel.search.SearchViewModel
import com.zenbyte.studio.presentation.viewmodel.state.ApiState

@Composable
fun CountriesScreen(countryList: State<ApiState<MyCountry>>, viewModel: SearchViewModel, onClick: () -> Unit) {

    val context = LocalPlatformContext.current

    if(countryList.value.isSuccess){
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = countryList.value.data) { country ->
                CountryItem(context = context, country = country) {
                    onClick.invoke()
                }
            }
        }
    }
    else if(countryList.value.isLoading){
        CountryShimmer()
    }else {
        ServerError {
            viewModel.getAllCountry()
        }
    }



}