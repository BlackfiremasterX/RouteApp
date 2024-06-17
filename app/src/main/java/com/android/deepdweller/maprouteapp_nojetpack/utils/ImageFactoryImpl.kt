package com.android.deepdweller.maprouteapp_nojetpack.utils

import com.android.deepdweller.maprouteapp_nojetpack.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.yandex.runtime.image.ImageProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageFactoryImpl @Inject constructor(
    private val context: Context
) : ImageFactory {
    private val case1 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case2 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case3 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case4 = ContextCompat.getDrawable(context, R.drawable.icon)

    private val density = context.resources.displayMetrics.density
    private val customTypeface = ResourcesCompat.getFont(context, R.font.sfui_display_medium)
    val case5 = ContextCompat.getDrawable(context, R.drawable.icon)
    val case6 = ContextCompat.getDrawable(context, R.drawable.icon)
    val case7 = ContextCompat.getDrawable(context, R.drawable.icon)
    val case8 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case9 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case10 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case11 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case12 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case13 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case14 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case15 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case16 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case17 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case18 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case19 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case20 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case21 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case22 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case23 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case24 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case25 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case26 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case27 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case28 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val case29 = ContextCompat.getDrawable(context, R.drawable.icon)
    private val star = ContextCompat.getDrawable(context, R.drawable.icon)
    private val user = ContextCompat.getDrawable(context, R.drawable.icon_round)
    private val userMove = ContextCompat.getDrawable(context, R.drawable.icon)

    private val textPaintBlack = Paint().apply {
        textSize = 14 * density
        style = Paint.Style.FILL
        isAntiAlias = true
        typeface = customTypeface
    }
    private val textPaintWhite = Paint().apply {
        textSize = 14 * density
        color = Color.WHITE
        style = Paint.Style.FILL
        isAntiAlias = true
        typeface = customTypeface
    }

    private fun getImageProvider(drawable: Drawable?): ImageProvider {
        val createBitmap = Bitmap.createBitmap(
            (drawable!!.intrinsicWidth / SCALE).toInt(),
            (drawable.intrinsicHeight / SCALE).toInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(createBitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return ImageProvider.fromBitmap(createBitmap)
    }

    override fun getUserMarkerMove(): ImageProvider {
        val createBitmap = Bitmap.createBitmap(
            (userMove!!.intrinsicWidth / SCALE / 1.4).toInt(),
            (userMove.intrinsicHeight / SCALE / 1.4).toInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(createBitmap)
        userMove.setBounds(0, 0, canvas.width, canvas.height)
        userMove.draw(canvas)
        return ImageProvider.fromBitmap(createBitmap)
    }

    override fun getUserMarker(): ImageProvider {
        val createBitmap = Bitmap.createBitmap(
            (user!!.intrinsicWidth / SCALE / 1.4).toInt(),
            (user.intrinsicHeight / SCALE / 1.4).toInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(createBitmap)
        user.setBounds(0, 0, canvas.width, canvas.height)
        user.draw(canvas)
        return ImageProvider.fromBitmap(createBitmap)
    }


    override fun getImageCase1(): ImageProvider? {
        return getImageProvider(case1)
    }

    override fun getImageCase2(): ImageProvider? {
        return getImageProvider(case1)
    }

    override fun getImageCase3(): ImageProvider? {
        return getImageProvider(case1)
    }

    override fun getImageCase4(): ImageProvider? {
        return getImageProvider(case1)
    }

    override fun getImageCase5(): ImageProvider? {
        return getImageProvider(case1)
    }

    override fun getImageCase6(): ImageProvider? {
        return getImageProvider(case1)
    }

    override fun getImageCase7(): ImageProvider? {
        return getImageProvider(case1)
    }

    companion object {
        const val SCALE = 2.8
    }
}