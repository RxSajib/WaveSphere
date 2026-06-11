package com.zenbyte.studio.wavesphere.ui.screen.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.ui.component.GeneralSetting
import com.zenbyte.studio.wavesphere.ui.component.HeightSpace
import com.zenbyte.studio.wavesphere.ui.component.OtherSetting
import com.zenbyte.studio.wavesphere.ui.component.PremiumStatusCard

@Composable
fun PlayerScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(state = rememberScrollState())){
        HeightSpace(height = 10.dp)
        PremiumStatusCard(modifier = Modifier.padding(horizontal = 16.dp))
        HeightSpace(height = 15.dp)
        GeneralSetting(modifier = Modifier.padding(horizontal = 16.dp))
        HeightSpace(height = 15.dp)
        OtherSetting(modifier = Modifier.padding(horizontal = 16.dp))
        HeightSpace(height = 10.dp)
    }
}