package com.zenbyte.studio.presentation.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.usecase.CountryListUseCase
import com.zenbyte.studio.domain.usecase.SearchChannelUseCase
import com.zenbyte.studio.domain.utils.Resource
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SearchViewModel"
@HiltViewModel
class SearchViewModel @Inject constructor(
    val countryListUseCase: CountryListUseCase,
    val searchChannelUseCase: SearchChannelUseCase
) : ViewModel() {

    private val searchInputMutableStateFlow = MutableStateFlow("")
    val searchInput = searchInputMutableStateFlow.asStateFlow()


    private val newsListMutableStateFlow = MutableStateFlow<List<MyChannel>>(emptyList())
    val newsList = newsListMutableStateFlow.asStateFlow()

    fun getNewsList(countryCode : String){
        viewModelScope.launch {
            val response = searchChannelUseCase.getChannelBySearch(tag = "news", order = "news", countryCode = countryCode)
            when(response){
                is Resource.Success -> {
                    MyCustomLogger.logMessageDebug(tag = TAG, message = response.data.toString())
                    newsListMutableStateFlow.emit(response.data?: emptyList())
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    newsListMutableStateFlow.emit(emptyList())
                }
            }
        }
    }


    fun inputSearchData(searchKey : String){
        viewModelScope.launch {
            searchInputMutableStateFlow.emit(searchKey)
        }
    }

    private var selectedMenuPositionMutableStateFlow = MutableStateFlow(1)
    val selectedMenuPosition = selectedMenuPositionMutableStateFlow.asStateFlow()

    private val countryListMutableStateFlow = MutableStateFlow<List<MyCountry>>(emptyList())
    val countryList = countryListMutableStateFlow.asStateFlow().map {country ->
        country.sortedBy { it.countryCode }
    }

    fun setSelectedMenuPosition(position: Int){
        viewModelScope.launch {
            selectedMenuPositionMutableStateFlow.emit(position)
        }
    }

    init {
        getAllCountry()
        getNewsList(
            countryCode = "in"
        )
    }

    fun getAllCountry(){
        viewModelScope.launch {
            val response = countryListUseCase.getCountryList()
            when(response){
                is Resource.Success -> {
                    countryListMutableStateFlow.emit(response.data?: emptyList())
                }
                is Resource.Error -> {
                    countryListMutableStateFlow.emit(emptyList())
                }
                is Resource.Loading -> {

                }
            }
        }
    }
}