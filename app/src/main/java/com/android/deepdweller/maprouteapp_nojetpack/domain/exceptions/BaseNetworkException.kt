package com.android.deepdweller.maprouteapp_nojetpack.domain.exceptions

import java.io.IOException

open class BaseNetworkException(
    override val message: String = "NetworkError",
    override val cause: Throwable? = null,
) : IOException(message, cause)