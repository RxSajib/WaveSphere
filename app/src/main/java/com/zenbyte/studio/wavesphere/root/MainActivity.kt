package com.zenbyte.studio.wavesphere.root

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Resources
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.annotation.OptIn
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.util.UnstableApi
import com.zenbyte.studio.wavesphere.service.PlayerService
import com.zenbyte.studio.presentation.ui.navigation.RootNavigation
import com.zenbyte.studio.presentation.ui.navigation.SampleDes
import com.zenbyte.studio.presentation.ui.screen.SplashScreen
import com.zenbyte.studio.presentation.ui.theme.WaveSphereTheme
import com.zenbyte.studio.presentation.viewmodel.mainActivity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.b3nedikt.app_locale.AppLocale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


@OptIn(UnstableApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainActivityViewModel by viewModels()

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(AppLocale.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaveSphereTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {
                  //  SplashScreen()
                    RootNavigation()
                }

            }
        }
    }
    override fun onStop() {
        super.onStop()
        lifecycleScope.launch {
            val continueInBackground = viewModel.isContinueInBackground.first()
            if (!continueInBackground) {
                viewModel.destroyMusic()
                stopService(Intent(this@MainActivity, PlayerService::class.java))
            }
        }
    }
}