package com.zenbyte.studio.presentation.viewmodel.splashScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.CountryListUseCase
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.domain.utils.Resource
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

private const val TAG = "SplashScreenViewModel"

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    val countryListUseCase: CountryListUseCase,
    val getChannelByCountryUseCase: GetChannelByCountryUseCase,
    private val localChannelUseCase: LocalChannelUseCase
) : ViewModel() {

    var navigateToHome = MutableSharedFlow<Boolean>()
    private var channelMutableStateFlow = MutableStateFlow<List<MyChannel>>(emptyList())
    val channelList = channelMutableStateFlow.asStateFlow()
    private val isLoadChannelMutableStateFlow = MutableStateFlow(false)
    val isLoadChannel = isLoadChannelMutableStateFlow.asStateFlow()
    var splashScreenState by mutableStateOf(false)

    init {
      //  getAllChannel()
      //  getAlLocalChannel()

        viewModelScope.launch {
            delay(3000.milliseconds)
            splashScreenState = true
        }
    }

    private fun getAlLocalChannel() {
        viewModelScope.launch {
            localChannelUseCase.getLocalChannelList().collect { myChannels ->
                channelMutableStateFlow.emit(myChannels)
            }
        }
    }

    private fun getAllChannel() {
        viewModelScope.launch {

/*
            if (localChannelUseCase.getLocalChannelList().first().isNotEmpty()) {
                navigateToHome.emit(true)
                return@launch
            }
*/

            isLoadChannelMutableStateFlow.update { true }

            when (val allCountry = countryListUseCase.getCountryList()) {

                is Resource.Success -> {

                    val countries = allCountry.data.orEmpty()

                    coroutineScope {

                        countries.map { country ->
                            async(Dispatchers.IO) {

                                MyCustomLogger.logMessageInfo(
                                    tag = TAG,
                                    message = "Loading channels for: ${country.name}"
                                )

                                getChannelByCountryUseCase.getChannelByCountry(
                                    countryName = country.name
                                )
                            }
                        }.awaitAll()
                    }

                    // Every country API call has completed here
                    isLoadChannelMutableStateFlow.update { false }

                    navigateToHome.emit(true)
                }

                is Resource.Loading -> {
                    // Handle loading
                }

                is Resource.Error -> {
                    isLoadChannelMutableStateFlow.update { false }
                    // Handle error
                }
            }
        }
    }


}