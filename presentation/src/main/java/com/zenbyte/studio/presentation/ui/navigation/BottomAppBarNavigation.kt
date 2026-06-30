package com.zenbyte.studio.presentation.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.serialization.json.Json
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.WidthGap
import com.zenbyte.studio.presentation.ui.screen.FavoriteScreen
import com.zenbyte.studio.presentation.ui.screen.HomeScreen
import com.zenbyte.studio.presentation.ui.screen.PlayerScreen
import com.zenbyte.studio.presentation.ui.screen.SearchScreen
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic


@Composable
fun BottomAppBarNavigation(rootBackStack: NavBackStack<NavKey>) {
    val appConfig = SavedStateConfiguration {
        this.serializersModule = SerializersModule {
            this.polymorphic(NavKey::class) {
                subclass(
                    AppDestination.BottomAppBar.Home::class,
                    AppDestination.BottomAppBar.Home.serializer()
                )
                subclass(
                    AppDestination.BottomAppBar.Search::class,
                    AppDestination.BottomAppBar.Search.serializer()
                )
                subclass(
                    AppDestination.BottomAppBar.Favorite::class,
                    AppDestination.BottomAppBar.Favorite.serializer()
                )
                subclass(
                    AppDestination.BottomAppBar.Player::class,
                    AppDestination.BottomAppBar.Player.serializer()
                )

            }
        }
    }

    val dashBoardBackStack = rememberNavBackStack(appConfig, AppDestination.BottomAppBar.Home)
    val searchBackStack = rememberNavBackStack(appConfig, AppDestination.BottomAppBar.Search)
    val favoriteBackStack =
        rememberNavBackStack(appConfig, AppDestination.BottomAppBar.Favorite)
    val profileBackStack = rememberNavBackStack(appConfig, AppDestination.BottomAppBar.Player)




    val AppDestinationSaver: Saver<AppDestination, String> = Saver(save = { destination ->
        Json.encodeToString(AppDestination.serializer(), destination)
    }, restore = { jsonString ->
        Json.decodeFromString(AppDestination.serializer(), jsonString)
    })

    var currentTab by rememberSaveable(
        stateSaver = AppDestinationSaver
    ) {
        mutableStateOf<AppDestination>(AppDestination.BottomAppBar.Home)
    }
    // Select the active back stack
    val activeBackStack: NavBackStack<NavKey> = when (currentTab) {
        is AppDestination.BottomAppBar.Home -> dashBoardBackStack
        is AppDestination.BottomAppBar.Search -> searchBackStack
        is AppDestination.BottomAppBar.Favorite -> favoriteBackStack
        is AppDestination.BottomAppBar.Player -> profileBackStack
        else -> dashBoardBackStack
    }





    Scaffold(topBar = {
        MyCustomAppBar(
            isBackButtonEnable = false,
            isActonButtonEnable = true,
            title = stringResource(R.string.app_name),
            homeHeaderEnable = true,
            onBackPress = {},
        )

    }, bottomBar = {
        Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(bottomEnd = 0.dp, bottomStart = 0.dp, topEnd = 10.dp, topStart = 10.dp)
            ) {


            NavigationBar(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
            ) {

                NavigationBarItem(
                    colors = NavigationBarItemColors(
                        selectedIconColor = buttonColor,
                        selectedTextColor = Color.Transparent,
                        selectedIndicatorColor = Color.Transparent,
                        unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        unselectedTextColor = Color.Transparent,
                        disabledIconColor = Color.Transparent,
                        disabledTextColor = Color.Transparent
                    ),
                    selected = currentTab is AppDestination.BottomAppBar.Home,
                    onClick = { currentTab = AppDestination.BottomAppBar.Home },
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = if (currentTab is AppDestination.BottomAppBar.Home) painterResource(
                                    R.drawable.icon_home_selected
                                ) else painterResource(R.drawable.icon_home),
                                null,
                                modifier = Modifier.size(24.dp)
                            )
                            WidthGap(width = 2.dp)
                            Text(
                                stringResource(R.string.home),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                )
                            )
                        }

                    },

                )
                NavigationBarItem(
                    selected = currentTab is AppDestination.BottomAppBar.Search,
                    onClick = { currentTab = AppDestination.BottomAppBar.Search },
                    colors = NavigationBarItemColors(
                        selectedIconColor = buttonColor,
                        selectedTextColor = Color.Transparent,
                        selectedIndicatorColor = Color.Transparent,
                        unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        unselectedTextColor = Color.Transparent,
                        disabledIconColor = Color.Transparent,
                        disabledTextColor = Color.Transparent
                    ),
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = if (currentTab is AppDestination.BottomAppBar.Search) painterResource(
                                    R.drawable.icon_search_selected
                                ) else painterResource(R.drawable.icon_search),
                                null,
                                modifier = Modifier.size(24.dp)
                            )
                            WidthGap(width = 2.dp)
                            Text(
                                text = stringResource(R.string.search),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                )
                            )
                        }

                    },


                    )

                NavigationBarItem(
                    colors = NavigationBarItemColors(
                        selectedIconColor = buttonColor,
                        selectedTextColor = Color.Transparent,
                        selectedIndicatorColor = Color.Transparent,
                        unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        unselectedTextColor = Color.Transparent,
                        disabledIconColor = Color.Transparent,
                        disabledTextColor = Color.Transparent
                    ),
                    selected = currentTab is AppDestination.BottomAppBar.Favorite,
                    onClick = { currentTab = AppDestination.BottomAppBar.Favorite },
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = if (currentTab is AppDestination.BottomAppBar.Favorite) painterResource(
                                    R.drawable.icon_heart_selected
                                ) else
                                    painterResource(R.drawable.icon_favorite_heart_hover_pinch),
                                null,
                                modifier = Modifier.size(24.dp)
                            )
                            WidthGap(width = 2.dp)
                            Text(
                                stringResource(R.string.favorite),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                )
                            )
                        }

                    },
                )

                NavigationBarItem(
                    colors = NavigationBarItemColors(
                        selectedIconColor = buttonColor,
                        selectedTextColor = Color.Transparent,
                        selectedIndicatorColor = Color.Transparent,
                        unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        unselectedTextColor = Color.Transparent,
                        disabledIconColor = Color.Transparent,
                        disabledTextColor = Color.Transparent
                    ),

                    selected = currentTab is AppDestination.BottomAppBar.Player,
                    onClick = { currentTab = AppDestination.BottomAppBar.Player },
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = if (currentTab is AppDestination.BottomAppBar.Player) painterResource(
                                    R.drawable.icon_user_selected
                                ) else painterResource(R.drawable.icon_user),
                                null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                stringResource(R.string.player),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                )
                            )
                        }

                    },
                    /*label = {

                }*/
                )
            }
        }

    }) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = activeBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<AppDestination.BottomAppBar.Home> {
                    HomeScreen()
                }
                entry<AppDestination.BottomAppBar.Search> {
                    SearchScreen(activeBackStack = activeBackStack, rootBackStack = rootBackStack)
                }

                entry<AppDestination.BottomAppBar.Favorite> {
                    FavoriteScreen()
                }
                entry<AppDestination.BottomAppBar.Player> {
                    PlayerScreen(rootBackStack = rootBackStack)
                }
            })
    }
}