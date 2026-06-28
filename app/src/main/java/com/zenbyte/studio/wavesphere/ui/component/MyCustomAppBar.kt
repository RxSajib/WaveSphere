package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.ui.component.WidthGap
import com.zenbyte.studio.presentation.ui.theme.buttonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCustomAppBar(
    homeHeaderEnable: Boolean = false,
    isBackButtonEnable: Boolean = true,
    isPremiumEnable: Boolean = true,
    isActonButtonEnable: Boolean = false,
    title: String = "WaveSphere",
    onBackPress: () -> Unit,
) {

    TopAppBar(
        title = {
            if (homeHeaderEnable) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                   Image(
                       painter = painterResource(com.zenbyte.studio.wavesphere.R.drawable.applogo),
                       contentDescription = null,
                       modifier = Modifier.size(45.dp),
                       colorFilter = ColorFilter.tint(color = buttonColor)
                   )
                    WidthGap(width = 10.dp)
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = title,
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            } else {
                Text(
                    text = title, style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

        },
        actions = {
            if(isPremiumEnable){
                PremiumTag(
                    onClick = {

                    }
                )
                WidthGap(width = 10.dp)
            }

        },
        navigationIcon = {
            if (isBackButtonEnable) {
                IconButton(onClick = {
                    onBackPress.invoke()
                }) {
                    Icon(
                        painter = painterResource(com.zenbyte.studio.wavesphere.R.drawable.ic_back),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

        },
    )
}

@Preview()
@Composable
fun MyCustomAppBarPreview() {
    MyCustomAppBar(
        isBackButtonEnable = true,
        isActonButtonEnable = true,
        title = "Title Toolbar",
        homeHeaderEnable = true,
        onBackPress = {},
    )
}