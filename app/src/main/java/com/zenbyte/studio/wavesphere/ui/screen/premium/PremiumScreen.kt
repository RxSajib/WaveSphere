package com.zenbyte.studio.wavesphere.ui.screen.premium

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.component.HeightSpace
import com.zenbyte.studio.wavesphere.ui.component.PremiumBenefitsCard

@Composable
fun PremiumScreen() {
    //   Scaffold(topBar = {}) { innerPadding ->
    Column(modifier = Modifier.fillMaxSize()) {
        HeightSpace(height = 10.dp)
        Text(
            text = stringResource(R.string.go_premium),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HeightSpace(height = 5.dp)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(R.string.premium_details),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth(.6f),
                textAlign = TextAlign.Center
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(R.drawable.premum_crown), contentDescription = null,
                modifier = Modifier.fillMaxWidth(.8f))
        }

        PremiumBenefitsCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
    }
    //  }
}


@Composable
@Preview
fun PremiumScreenPreview() {
    PremiumScreen()
}