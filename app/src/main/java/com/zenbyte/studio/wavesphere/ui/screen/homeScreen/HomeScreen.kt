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
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.wavesphere.ui.component.HeightSpace
import com.zenbyte.studio.wavesphere.ui.component.HomeHeader
import com.zenbyte.studio.wavesphere.ui.component.NowPlayingComponent

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalPlatformContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState())
    ) {

        HomeHeader()
        HeightSpace(height = 15.dp)

        NowPlayingComponent(context)
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}