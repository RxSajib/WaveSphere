package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R

@Composable
fun MyCustomMenuGroup(onClickCountries: () -> Unit, onClickGenres: () -> Unit, onClickNews: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        MyCustomMenuButton(
            modifier = Modifier.weight(1f),
            isSelected = true,
            title = stringResource(R.string.countries),
            onClick = {
                onClickCountries.invoke()
            }
        )
        WidthSpace(width = 15.dp)
        MyCustomMenuButton(
            modifier = Modifier.weight(1f),
            isSelected = false,
            title = stringResource(R.string.genres),
            onClick = {
                onClickGenres.invoke()
            }
        )
        WidthSpace(width = 15.dp)
        MyCustomMenuButton(
            modifier = Modifier.weight(1f),
            isSelected = false,
            title = stringResource(R.string.news),
            onClick = {
                onClickNews.invoke()
            }
        )
    }
}

@Composable
@Preview
fun MyCustomMenuGroupPreview() {

}