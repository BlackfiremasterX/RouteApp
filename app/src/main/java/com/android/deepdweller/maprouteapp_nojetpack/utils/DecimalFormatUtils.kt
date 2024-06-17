package com.android.deepdweller.maprouteapp_nojetpack.utils

import java.math.RoundingMode
import java.text.DecimalFormat

object DecimalFormatUtils {
    fun String.numFormat(): String {
        try {
            val amount = this.toDouble()
            val bd = amount.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)

            val df = DecimalFormat().apply {
                maximumFractionDigits = 2
                minimumFractionDigits = 2
                isGroupingUsed = true
                groupingSize = 3
            }

            return df.format(bd).replace(".", ",")
        } catch (t: Throwable) {
            return ""
        }
    }
}