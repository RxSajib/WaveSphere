package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.viewmodel.search.SearchViewModel

@Composable
fun MyCustomMenuGroup(viewModel: SearchViewModel, modifier: Modifier, onClickCountries: () -> Unit, onClickGenres: () -> Unit, onClickNews: () -> Unit) {

    val selectedMenuPosition = viewModel.selectedMenuPosition.collectAsStateWithLifecycle()

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        MyCustomMenuButton(
            modifier = Modifier.weight(1f),
            isSelected = selectedMenuPosition.value == 1,
            title = stringResource(R.string.countries),
            onClick = {
                viewModel.setSelectedMenuPosition(1)
                onClickCountries.invoke()
            }
        )
        WidthGap(width = 15.dp)
        MyCustomMenuButton(
            modifier = Modifier.weight(1f),
            isSelected = selectedMenuPosition.value == 2,
            title = stringResource(R.string.genres),
            onClick = {
                viewModel.setSelectedMenuPosition(2)
                onClickGenres.invoke()
            }
        )
        WidthGap(width = 15.dp)
        MyCustomMenuButton(
            modifier = Modifier.weight(1f),
            isSelected = selectedMenuPosition.value == 3,
            title = stringResource(R.string.news),
            onClick = {
                viewModel.setSelectedMenuPosition(3)
                onClickNews.invoke()
            }
        )
    }
}

@Composable
@Preview
fun MyCustomMenuGroupPreview() {

}