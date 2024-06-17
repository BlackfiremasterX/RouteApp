package com.android.deepdweller.maprouteapp_nojetpack.domain.exceptions

class NoInternetException(
    override val cause: Throwable? = null,
) : BaseNetworkException("NoInternetException", cause)
