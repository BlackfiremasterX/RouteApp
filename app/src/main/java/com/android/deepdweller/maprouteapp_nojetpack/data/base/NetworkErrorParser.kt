package com.android.deepdweller.maprouteapp_nojetpack.data.base

import retrofit2.HttpException
import com.android.deepdweller.maprouteapp_nojetpack.domain.exceptions.BaseNetworkException
import com.android.deepdweller.maprouteapp_nojetpack.domain.exceptions.HttpNetException
import com.android.deepdweller.maprouteapp_nojetpack.domain.exceptions.NoInternetException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkErrorParser @Inject constructor() {
    operator fun invoke(th: Throwable) {
        throw when (th) {
            is HttpException -> HttpNetException(th.code(), th.message(), th)
            is UnknownHostException -> NoInternetException(th)
            is BaseNetworkException -> th
            else -> IOException(th)
        }
    }
}
