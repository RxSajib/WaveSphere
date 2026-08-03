package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar

@Composable
fun TrendingStationsScreen(modifier: Modifier = Modifier, rootBackStack: NavBackStack<NavKey>) {
    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {

        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(com.zenbyte.studio.presentation.R.string.trending_stations)
                ) {
                    rootBackStack.removeLastOrNull()
                }
            }
        ) { innerPadding ->

            Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center){
                Text(
                    text = "TS"
                )
            }
        }

    }
}