package com.zenbyte.studio.presentation.viewmodel.getChannelByCountry

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "GetChannelByCountry"
@HiltViewModel
class GetChannelByCountryViewModel @Inject constructor(
    val getChannelByCountryUseCase: GetChannelByCountryUseCase
) : ViewModel() {

    init {
        getChannelByCountry(countryName = "Bangladesh")
    }

    fun getChannelByCountry(countryName: String) {
        viewModelScope.launch {
            when(val response = getChannelByCountryUseCase.getChannelByCountry(countryName)){
               is Resource.Success -> {
                   Log.d(TAG, "getChannelByCountry: ${response.data}")
               }
               is Resource.Error -> {
                   Log.d(TAG, "error: ${response.message}")
               }
               is Resource.Loading -> {
                   Log.d(TAG, "loading: ")
               }
           }
        }
    }
}