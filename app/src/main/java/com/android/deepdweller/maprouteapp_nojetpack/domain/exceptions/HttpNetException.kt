package com.android.deepdweller.maprouteapp_nojetpack.domain.exceptions

class HttpNetException(
    val code: Int,
    val statusMessage: String,
    override val cause: Throwable? = null,
) : BaseNetworkException("HttpException code: $code, message: $statusMessage", cause) {
    companion object {
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403

        fun unauthorized() = HttpNetException(UNAUTHORIZED, "Unauthorized")
    }

    fun isUnauthorized() = code == UNAUTHORIZED

    fun isForbidden() = code == FORBIDDEN
}
