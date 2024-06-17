package com.android.deepdweller.maprouteapp_nojetpack.domain.exceptions

class OtherNetworkException(
    override val message: String,
    override val cause: Throwable? = null,
) : BaseNetworkException(message, cause)
