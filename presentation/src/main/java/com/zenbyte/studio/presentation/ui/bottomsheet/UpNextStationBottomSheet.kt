package com.zenbyte.studio.presentation.ui.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.ChannelItem
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.WidthGap
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.viewmodel.home.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpNextStationBottomSheet(channelList : List<MyChannel> = emptyList(), viewModel: HomeViewModel, onDismissRequest: () -> Unit, ) {

    val scope = rememberCoroutineScope()
    val context = LocalPlatformContext.current
    val state = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(onDismissRequest = {onDismissRequest.invoke()}, sheetState = state) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.applogo),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    colorFilter = ColorFilter.tint(color = buttonColor)
                )
                WidthGap(width = 10.dp)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Up Next",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Next tracks in queue",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            fontSize = adjustedFontSize(10f)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(onClick = {
                    scope.launch {
                        state.hide()
                        onDismissRequest.invoke()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }

            HeightGap(height = 20.dp)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(channelList) { channel ->
                    ChannelItem(
                        context = context,
                        myChannel = channel,
                        isPlaying = viewModel.isPlaying(myChannel = channel)
                            .collectAsStateWithLifecycle(false).value,
                        modifier = Modifier,
                        isBuffering = viewModel.isBufferingChannel(myChannel = channel)
                            .collectAsStateWithLifecycle(false).value,
                        isChannelFavorite =
                            viewModel.getSaveChannel(channelID = channel.stationuuid)
                                .collectAsStateWithLifecycle(false).value,
                        onClickFavorite = { myChannel ->
                            viewModel.saveChannel(myChannel = myChannel)
                        },
                        onMediaController = { myChannel ->
                            viewModel.mediaPlayController(
                                myChannel = myChannel,
                                channels = channelList,
                                index = channelList.indexOf(channel)
                            )
                        })
                }
            }
        }
    }
}