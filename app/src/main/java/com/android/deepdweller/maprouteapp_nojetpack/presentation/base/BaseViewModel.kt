package com.android.deepdweller.maprouteapp_nojetpack.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected abstract val screenName: String

    protected fun <T> Flow<T>.inViewModel(): Job = launchIn(viewModelScope)

    protected fun inViewModel(block: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch(block = block)

    protected fun <T> Flow<T>.inIO(): Flow<T> = flowOn(Dispatchers.IO)
}