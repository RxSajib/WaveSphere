package com.zenbyte.studio.wavesphere.ui.screen.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.ui.component.HomeHeader

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

        Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(state = rememberScrollState())) {

            HomeHeader()
        }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}