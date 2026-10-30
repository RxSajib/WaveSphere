package com.zenbyte.studio.presentation.ui.screen

import androidx.annotation.keep.Unspecified
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlexBasis.Companion.Dp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.bottomsheet.SleepTimerSheet
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.WidthGap
import com.zenbyte.studio.presentation.viewmodel.playerView.PlayerViewModel
import com.zenbyte.studio.presentation.ui.component.LiveTag
import com.zenbyte.studio.presentation.ui.component.MusicController
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.PremiumTag
import com.zenbyte.studio.presentation.ui.component.QuickAction
import com.zenbyte.studio.presentation.ui.component.TightWrapText
import com.zenbyte.studio.presentation.ui.component.UpNextStationCard
import com.zenbyte.studio.presentation.ui.navigation.AppDestination
import com.zenbyte.studio.presentation.ui.theme.genresColor
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import dev.vivvvek.seeker.Seeker
import dev.vivvvek.seeker.SeekerDefaults
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape


private const val TAG = "PlayerViewScreen"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerViewScreen(
    channelData: AppDestination.Dest.PlayerView,
    rootBackStack: NavBackStack<NavKey>
) {

    val coilsContext = LocalPlatformContext.current
    val viewModel: PlayerViewModel = hiltViewModel()
    val volume = viewModel.volume.collectAsStateWithLifecycle()

    val currentChannelByPlayer by viewModel.currentChannel.collectAsStateWithLifecycle()
    val isPlaying by viewModel.isPlaying.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isPlayingChannel by viewModel.isPlayingChannel.collectAsStateWithLifecycle(false)
    val isChannelSaved by viewModel.isChannelSaved.collectAsStateWithLifecycle(false)
    val isShowLiveIndicator by viewModel.isShowLiveIndicator.collectAsStateWithLifecycle(false)

    // Sync UI with the selected channel, then follow service updates (Next/Prev)
    val currentChannel = currentChannelByPlayer ?: channelData.channel

    LaunchedEffect(currentChannel) {
        viewModel.updateChannelUID(value = currentChannel.stationuuid)
        viewModel.updatePlayingUID(value = currentChannel.stationuuid)
        viewModel.selectedChannel(selectedChannel = channelData.channel)
    }

    LaunchedEffect(Unit) {
    //    playerService?.setChannelList(channelList)
    }

 //   MyCustomLogger.logMessageInfo(tag = TAG, message = "is playing media $buttonEnable")
    MyCustomLogger.logMessageInfo(tag = TAG, message = "selected channel id ${channelData.channel.stationuuid}")



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.now_playing),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.down_chevron_svgrepo_com),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.more_vertical_svgrepo_com),
                            contentDescription = null
                        )
                    }
                }
            )

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PremiumTag {

            }
            HeightGap(height = 15.dp)
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .aspectRatio(1f),
                model = ImageRequest.Builder(coilsContext)
                    .data(channelData.channel.favicon).size(500).build(),
                placeholder = painterResource(R.drawable.applogowhite),
                error = painterResource(R.drawable.applogowhite),
                contentDescription = null
            )
            HeightGap(height = 15.dp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = channelData.channel.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
                WidthGap(width = 5.dp)
                Icon(
                    painter = painterResource(R.drawable.ic_verified),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = channelData.channel.tags,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            HeightGap(height = 15.dp)
            if(isShowLiveIndicator){
                LiveTag()
            }
            if (isLoading) {
                androidx.compose.material3.CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp),
                    color = genresColor
                )
            }
            HeightGap(height = 15.dp)
            Row(modifier = Modifier.fillMaxWidth()) {
                QuickAction(
                    isSavedChannel = isChannelSaved,
                    icon = painterResource(R.drawable.icon_favorite_heart_hover_pinch),
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.favorite)
                ){
                    viewModel.saveChannel(myChannel = currentChannel)
                }

                QuickAction(
                    icon = painterResource(R.drawable.icon_clock),
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.sleep_timer)
                ){
                    viewModel.showSleepTimerDialog = true
                }

                QuickAction(
                    icon = painterResource(R.drawable.record_circle),
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.record),
                    isPremium = true
                ){

                }
                QuickAction(
                    icon = painterResource(R.drawable.share_svgrepo_com),
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.share)
                ){

                }
            }

            HeightGap(height = 15.dp)


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.volume_mute_svgrepo_com),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Seeker(
                    value = volume.value,
                    colors = SeekerDefaults.seekerColors(
                        thumbColor = genresColor,
                        progressColor = genresColor.copy(alpha = 0.5f)
                    ),
                    range = 1f..100f,
                    onValueChange = { value ->
                        viewModel.updateVolume(value)
                    },
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.volume_up_svgrepo_com),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
            HeightGap(height = 15.dp)
/*
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                MusicController(icon = painterResource(R.drawable.previous_svgrepo_com)) {
                    viewModel.previousPlayBack()
                }
                WidthGap(width = 10.dp)
                MusicController(
                    isPlayPushButton = true,
                    icon = if (isLoading) painterResource(R.drawable.record_circle) // Buffering indicator
                    else if (isPlayingChannel) painterResource(R.drawable.pause)
                    else painterResource(R.drawable.system_solid_26_play_hover_play)
                ) {
                    if (isPlayingChannel) {
                        viewModel.pauseAudio()
                    } else {
                        viewModel.playAudio(myChannel = channelList, channelList.indexOf(currentChannel))
                    }
                }
                WidthGap(width = 10.dp)
                MusicController(icon = painterResource(R.drawable.next_svgrepo_com)) {
                    viewModel.nextPlayBack()
                }
            }
*/

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button (
                    onClick = {

                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(76.dp),
                    enabled = true,
                    shape = AbsoluteSmoothCornerShape(
                        cornerRadiusTL = 60.dp,
                        smoothnessAsPercentTR = 60,
                        cornerRadiusTR = 14.dp,
                        smoothnessAsPercentTL = 60,
                        cornerRadiusBL = 60.dp,
                        smoothnessAsPercentBR = 60,
                        cornerRadiusBR = 14.dp,
                        smoothnessAsPercentBL = 60
                    ),
                    contentPadding = PaddingValues(horizontal = 10.dp)
                ) {
                    Icon(
                        Icons.Rounded.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    TightWrapText(
                        text = "Play",
                        modifier = Modifier.padding(end = 4.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        lineHeight = 20.sp
                    )
                }

                FilledTonalButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(76.dp),
                    enabled = false,
                    shape = AbsoluteSmoothCornerShape(
                        cornerRadiusTL = 14.dp,
                        smoothnessAsPercentTR = 60,
                        cornerRadiusTR = 60.dp,
                        smoothnessAsPercentTL = 60,
                        cornerRadiusBL = 14.dp,
                        smoothnessAsPercentBR = 60,
                        cornerRadiusBR = 60.dp,
                        smoothnessAsPercentBL = 60
                    ),
                    contentPadding = PaddingValues(horizontal = 10.dp)
                ) {
                    Icon(
                        Icons.Rounded.Shuffle,
                        contentDescription = "",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    TightWrapText(
                        text = "",
                        modifier = Modifier.padding(end = 4.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        lineHeight = 20.sp
                    )
                }
            }

            HeightGap(height = 10.dp)
            Spacer(modifier = Modifier.weight(1f))
            UpNextStationCard(
                myChannel =  MyChannel(
                    stationuuid = "station-002",
                    name = "Foorti 88.0 FM",
                    codec = "MP3",
                    country = "Bangladesh",
                    url = "https://example.com/foorti",
                    urlResolved = "https://example.com/foorti/stream",
                    favicon = "https://example.com/foorti.png",
                    language = "bengali",
                    votes = 950,
                    tags = "pop,music,entertainment",
                    lastcheckok = 1,
                    sslError = 0
                )
            )


            if(viewModel.showSleepTimerDialog){
                SleepTimerSheet()
            }
        }
    }
}


