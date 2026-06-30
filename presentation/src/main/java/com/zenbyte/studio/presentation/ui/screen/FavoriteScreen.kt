package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.stevdza_san.swipeable.Swipeable
import com.stevdza_san.swipeable.domain.ActionCustomization
import com.stevdza_san.swipeable.domain.HapticFeedbackConfig
import com.stevdza_san.swipeable.domain.SwipeAction
import com.stevdza_san.swipeable.domain.SwipeBackground
import com.stevdza_san.swipeable.domain.SwipeBehavior
import com.zenbyte.studio.presentation.viewmodel.favorite.FavoriteChannelViewModel
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import com.zenbyte.studio.presentation.ui.component.ChannelItem
import com.zenbyte.studio.presentation.ui.component.EmptyChannel

private const val TAG = "FavoriteScreen"

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    val contextCoil = LocalPlatformContext.current
    val viewModel: FavoriteChannelViewModel = hiltViewModel()
    val favoriteChannelList = viewModel.favoriteChannel.collectAsStateWithLifecycle(emptyList())


    MyCustomLogger.logMessageInfo(
        tag = TAG,
        message = "channel list ${favoriteChannelList.value.size}"
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (favoriteChannelList.value.isEmpty()) {
            EmptyChannel()
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(
                    items = favoriteChannelList.value,
                    key = { it.stationuuid }
                ) { myChannel ->

                    Swipeable(
                        behavior = SwipeBehavior.REVEAL,
                        rightRevealActions = listOf(
                            SwipeAction(
                                customization = ActionCustomization(
                                    icon = Icons.Default.Delete,
                                    iconColor = Color.Black,
                                    containerColor = Color.Transparent
                                ),
                                onAction = { viewModel.removeChannel(channelID = myChannel.stationuuid) }
                            )
                        ),
                        rightBackground = SwipeBackground.linearGradient(
                            colors = listOf(Color.Transparent, Color.Transparent)
                        ),
                        hapticFeedbackConfig = HapticFeedbackConfig.Default
                    ) {
                        ChannelItem(
                            context = contextCoil,
                            myChannel = myChannel,
                            modifier = Modifier
                        )
                    }

                }
            }


        }

    }
}