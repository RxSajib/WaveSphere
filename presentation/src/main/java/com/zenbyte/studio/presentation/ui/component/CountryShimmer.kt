package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun CountryShimmerItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp).shimmer(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .aspectRatio(1.5f)
                .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))

        )
        WidthGap(width = 16.dp)
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .height(15.dp)
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
            )
            HeightGap(height = 5.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .height(15.dp)
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
            )
        }
    }
}

@Composable
fun CountryShimmer() {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(state = rememberScrollState())) {
        repeat(20) {
            CountryShimmerItem()
        }
    }
}

@Composable
@Preview
fun CountryShimmerItemPreview() {
    CountryShimmer()
}