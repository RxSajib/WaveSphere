package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import com.zenbyte.studio.presentation.R

@Composable
fun MyCustomStationShimmerItem(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f), contentAlignment = Alignment.Center
        ) {



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .shimmer(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .aspectRatio(1f)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
                        .shimmer(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(10.dp))
                            .aspectRatio(1f),
                        painter = painterResource(R.drawable.shimmerlogo),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }

            }
        }

       /* Text(
            text = myChannel.name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = adjustedFontSize(10f)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = myChannel.tags,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                fontWeight = FontWeight.W400,
                fontSize = adjustedFontSize(8f)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )*/
    }
}

@Composable
@Preview
fun MyCustomStationShimmer() {
    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)) {
        items(30){
            MyCustomStationShimmerItem()
        }
    }
}