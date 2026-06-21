package com.zenbyte.studio.wavesphere.ui.screen.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.wavesphere.ui.component.GeneralSetting
import com.zenbyte.studio.wavesphere.ui.component.HeightSpace
import com.zenbyte.studio.wavesphere.ui.component.OtherSetting
import com.zenbyte.studio.wavesphere.ui.component.PremiumStatusCard
import com.zenbyte.studio.wavesphere.ui.navigation.AppDestination

@Composable
fun PlayerScreen(rootBackStack: NavBackStack<NavKey>) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(state = rememberScrollState())){
        HeightSpace(height = 10.dp)
        PremiumStatusCard(modifier = Modifier.padding(horizontal = 16.dp))
        HeightSpace(height = 15.dp)
        GeneralSetting(modifier = Modifier.padding(horizontal = 16.dp))
        HeightSpace(height = 15.dp)
        OtherSetting(modifier = Modifier.padding(horizontal = 16.dp), onClickAbout = {
            rootBackStack.add(
                AppDestination.Dest(firstDestName = AppDestination.Dest.AboutUs::class.simpleName?: "")
            )
        })
        HeightSpace(height = 10.dp)
    }
}