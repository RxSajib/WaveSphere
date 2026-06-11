package com.zenbyte.studio.wavesphere.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.wavesphere.BuildConfig
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.component.MyCustomAppBar
import com.zenbyte.studio.wavesphere.ui.component.NowPlayingComponent
import com.zenbyte.studio.wavesphere.ui.navigation.BottomAppBarNavigation
import com.zenbyte.studio.wavesphere.ui.screen.channelByCountry.ChannelByCountry
import com.zenbyte.studio.wavesphere.ui.screen.countryListScreen.CountryListScreen
import com.zenbyte.studio.wavesphere.ui.screen.homeScreen.HomeScreen
import com.zenbyte.studio.wavesphere.ui.theme.WaveSphereTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaveSphereTheme {
                    Box(modifier = Modifier.fillMaxSize()){
                        BottomAppBarNavigation()
                    }

            }
        }
    }
}