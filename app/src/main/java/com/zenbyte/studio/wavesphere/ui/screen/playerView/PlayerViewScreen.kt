package com.zenbyte.studio.wavesphere.ui.screen.playerView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.WidthGap
import com.zenbyte.studio.presentation.viewmodel.playerView.PlayerViewModel
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.app.MyApplication
import com.zenbyte.studio.wavesphere.root.LocalPlayerService
import com.zenbyte.studio.wavesphere.service.PlayerService
import com.zenbyte.studio.wavesphere.ui.component.LiveTag
import com.zenbyte.studio.wavesphere.ui.component.MusicController
import com.zenbyte.studio.wavesphere.ui.component.PremiumTag
import com.zenbyte.studio.wavesphere.ui.component.QuickAction
import com.zenbyte.studio.wavesphere.ui.navigation.AppDestination
import com.zenbyte.studio.presentation.ui.theme.genresColor
import dev.vivvvek.seeker.Seeker
import dev.vivvvek.seeker.SeekerDefaults

private const val TAG = "PlayerViewScreen"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerViewScreen(channelData: AppDestination.Dest.PlayerView) {

    val coilsContext = LocalPlatformContext.current
    val context = LocalContext.current
    val viewModel: PlayerViewModel = hiltViewModel()
    val volume = viewModel.volume.collectAsStateWithLifecycle()
    val playerService = LocalPlayerService.current
    val channelList = channelData.channelList

    val isLoading = playerService?.isLoading?.collectAsStateWithLifecycle()
    val isPlaying = playerService?.playing?.collectAsStateWithLifecycle()
    val isButtonEnable by viewModel.isButtonEnable.collectAsStateWithLifecycle(false)
    val isChannelSaved by viewModel.isChannelSaved.collectAsStateWithLifecycle(false)

    // Sync UI with the selected channel, then follow service updates (Next/Prev)
    var isSynced by remember(channelData.channel) { mutableStateOf(false) }
    var currentChannel by remember(channelData.channel) { mutableStateOf(channelData.channel) }
    val serviceChannel by playerService?.currentChannelFlow?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(null) }

    LaunchedEffect(serviceChannel) {
        if (serviceChannel?.stationuuid == channelData.channel.stationuuid) {
            isSynced = true
        }
        if (isSynced && serviceChannel != null) {
            currentChannel = serviceChannel!!
        }
    }

    LaunchedEffect(isPlaying?.value) {
        viewModel.updatePlayingUID(value = MyApplication.exoPlayer.currentMediaItem?.mediaId?: "")
    }
    LaunchedEffect(currentChannel) {
        viewModel.updateChannelUID(value = currentChannel.stationuuid)
    }

    LaunchedEffect(Unit) {
        playerService?.setChannelList(channelList)
    }




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
                .padding(innerPadding)
                .padding(16.dp),
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
                    .data(currentChannel.favicon).size(500).build(),
                placeholder = painterResource(R.drawable.applogowhite),
                error = painterResource(R.drawable.applogowhite),
                contentDescription = null
            )
            HeightGap(height = 15.dp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = currentChannel.name,
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
                text = currentChannel.tags,
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
            LiveTag()
            HeightGap(height = 15.dp)
            Row(modifier = Modifier.fillMaxWidth()) {
                QuickAction(
                    isSavedChannel = isChannelSaved,
                    icon = painterResource(R.drawable.icon_favorite_heart_hover_pinch),
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.favorite)
                ){
                    viewModel.saveChannel(myChannel = serviceChannel?: MyChannel())
                }

                QuickAction(
                    icon = painterResource(R.drawable.icon_clock),
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.sleep_timer)
                ){

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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                MusicController(icon = painterResource(R.drawable.previous_svgrepo_com)) {
                    playerService?.playPrevious()
                }
                WidthGap(width = 10.dp)
                MusicController(
                    isLoading = isLoading?.value == true,
                    isPlayPushButton = true,
                    icon = if (isButtonEnable) painterResource(R.drawable.pause) else painterResource(
                        R.drawable.system_solid_26_play_hover_play
                    )
                ) {
                    if(isButtonEnable){
                        if(!MyApplication.exoPlayer.isPlaying){
                            val intent = Intent(context, PlayerService::class.java)
                            ContextCompat.startForegroundService(context, intent)
                            playerService?.playChannel(currentChannel)
                        }else {
                            MyApplication.exoPlayer.pause()
                        }
                    }else {
                        val intent = Intent(context, PlayerService::class.java)
                        ContextCompat.startForegroundService(context, intent)
                        playerService?.playChannel(currentChannel)
                    }
                }
                WidthGap(width = 10.dp)
                MusicController(icon = painterResource(R.drawable.next_svgrepo_com)) {
                    playerService?.playNext()
                }
            }
        }
    }
}

