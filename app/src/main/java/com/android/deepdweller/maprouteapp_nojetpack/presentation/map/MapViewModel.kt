package com.android.deepdweller.maprouteapp_nojetpack.presentation.map

import com.android.deepdweller.maprouteapp_nojetpack.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
) : BaseViewModel() {
    override val screenName: String = "Map"

    private val _apiKeyFlow = MutableStateFlow<String?>(null)
    val apiKeyFlow: StateFlow<String?> = _apiKeyFlow

    fun setApiKey(apiKey: String) {
        _apiKeyFlow.value = apiKey
    }

    fun getApiKey(): String? {
        return apiKeyFlow.value
    }

}