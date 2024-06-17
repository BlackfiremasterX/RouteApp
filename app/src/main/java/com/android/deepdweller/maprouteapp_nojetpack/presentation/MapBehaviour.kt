package com.android.deepdweller.maprouteapp_nojetpack.presentation

import android.graphics.PointF
import com.android.deepdweller.maprouteapp_nojetpack.utils.ImageFactory
import com.yandex.mapkit.Animation
import com.yandex.mapkit.Animation.Type.SMOOTH
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.Cluster
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.RotationType
import com.yandex.runtime.image.ImageProvider

object MapBehaviour {
    private const val ZOOM_DEFAULT = 2
    private const val DURATION_DEFAULT = 1F

    fun reactOnClusterTap(cluster: Cluster, map: Map): Boolean {
        val latitude = cluster.appearance.geometry.latitude
        val longitude = cluster.appearance.geometry.longitude

        map.move(Point(latitude, longitude), animDuration = 0.5F)
        return true
    }

    fun zoomIn(map: Map) {
        map.move(map.cameraPosition.target)
    }

    fun zoomOut(map: Map) {
        map.move(map.cameraPosition.target, isZoomIn = false)
    }

    fun getPartnerImage(id: String?, imageFactory: ImageFactory): ImageProvider? {
        val imageProvider: ImageProvider? =
            when (id) {
//                CASE1 -> imageFactory.getImageCase1()
                else -> imageFactory.getImageCase1()
            }
        return imageProvider
    }

    private fun Map.move(
        target: Point,
        animDuration: Float = DURATION_DEFAULT,
        zoom: Int = ZOOM_DEFAULT,
        isZoomIn: Boolean = true
    ) {
        val zoomValue = if (isZoomIn) this.cameraPosition.zoom + zoom
        else this.cameraPosition.zoom - zoom

        this.move(
            CameraPosition(target, zoomValue, 0.0f, 0.0f),
            Animation(SMOOTH, animDuration),
            null
        )
    }

    fun PlacemarkMapObject.setMarker(isMove: Boolean, imageFactory: ImageFactory) {
        (if (isMove) imageFactory.getUserMarkerMove() else imageFactory.getUserMarker())?.let {
            this.setIcon(
                it,
                IconStyle().setAnchor(PointF(0.5f, 0.5f))
                    .setZIndex(0f)
                    .setScale(1f)
                    .setRotationType(RotationType.ROTATE)
            )
        }
    }
}