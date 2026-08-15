package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar

@Composable
fun AboutScreen(rootBackStack : NavBackStack<NavKey>) {
    Scaffold(
        topBar = {
            MyCustomAppBar(
                isPremiumEnable = false,
                title = stringResource(R.string.about)
            ) {
                rootBackStack.removeLastOrNull()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(state = rememberScrollState())
                .padding(16.dp)
        ) {

        }
    }
}