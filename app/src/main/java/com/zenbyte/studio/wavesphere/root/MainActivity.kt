package com.zenbyte.studio.wavesphere.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.zenbyte.studio.wavesphere.ui.navigation.BottomAppBarNavigation
import com.zenbyte.studio.wavesphere.ui.navigation.RootNavigation
import com.zenbyte.studio.wavesphere.ui.theme.WaveSphereTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaveSphereTheme {
                    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)){
                        RootNavigation()
                    }

            }
        }
    }
}