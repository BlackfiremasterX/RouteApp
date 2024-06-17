package com.android.deepdweller.maprouteapp_nojetpack.utils

import com.yandex.runtime.image.ImageProvider

interface ImageFactory {
    fun getUserMarker():ImageProvider?
    fun getUserMarkerMove():ImageProvider?

    fun getImageCase1(): ImageProvider?
    fun getImageCase2(): ImageProvider?
    fun getImageCase3(): ImageProvider?
    fun getImageCase4(): ImageProvider?
    fun getImageCase5(): ImageProvider?
    fun getImageCase6(): ImageProvider?
    fun getImageCase7(): ImageProvider?

}