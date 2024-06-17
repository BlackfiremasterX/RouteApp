package com.android.deepdweller.maprouteapp_nojetpack.utils

import LocationClient
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class LocationHelper @JvmOverloads constructor(
    private val context: Context
) : LocationClient {

    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(): Flow<Location> {
        return callbackFlow {
            val fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)
            val location = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 6000)
                .setMinUpdateIntervalMillis(3000)
                .setMinUpdateDistanceMeters(10F)
                .build()
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch { send(location) }
                    }
                }
            }
            fusedLocationProviderClient.requestLocationUpdates(
                location,
                locationCallback,
                Looper.getMainLooper()
            )
            awaitClose {
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            }
        }
    }
    @SuppressLint("MissingPermission")
    override fun getLastLocation(): Flow<Location> {
        return callbackFlow {
            val fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    launch {
                        if (location != null) {
                            send(location)
                        }
                    }
                }
            awaitClose {
            }
        }
    }
}