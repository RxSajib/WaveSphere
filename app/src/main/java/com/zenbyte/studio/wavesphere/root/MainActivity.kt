package com.zenbyte.studio.wavesphere.root

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.media3.common.util.UnstableApi
import com.zenbyte.studio.wavesphere.service.PlayerService
import com.zenbyte.studio.wavesphere.ui.navigation.RootNavigation
import com.zenbyte.studio.presentation.ui.theme.WaveSphereTheme
import dagger.hilt.android.AndroidEntryPoint

val LocalPlayerService = compositionLocalOf<PlayerService?> { null }

@OptIn(UnstableApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var playerService by mutableStateOf<PlayerService?>(null)
    private var isBound by mutableStateOf(false)

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as PlayerService.PlayerBinder
            playerService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            playerService = null
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, PlayerService::class.java).also { intent ->
            bindService(intent, connection, BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaveSphereTheme {
                CompositionLocalProvider(LocalPlayerService provides playerService) {
                    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)){
                        RootNavigation()
                    }
                }
            }
        }
    }
}